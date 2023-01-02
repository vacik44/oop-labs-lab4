package oop.labs.lab4.data.repos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class RecordsRepository
{
    protected final String CONTROL_FILE_NAME = ".ctrl";
    protected final String RECORD_NAME_PATTERN = "record#%s";
    protected final String RECORD_EXTENSION = ".rec";


    protected final String rootPath;
    protected final Controller controller;
    protected final ObjectMapper serializationUnit;


    protected void throwIfDisconnected() { if (!connected()) throw new RepositoryDisconnectedException(); }
    protected void throwIfAlreadyConnected() { if (connected()) throw new RepositoryReconnectionAttemptException(); }

    public boolean connected() { return controller.connected(); }
    @SuppressWarnings("BooleanMethodIsAlwaysInverted") public boolean exists() { return controller.exists(); }


    protected ObjectMapper getRepositoryInfoMapper()
    {
        var mapper = new ObjectMapper(new YAMLFactory());

        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, true);
        mapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);

        return mapper;
    }

    public RecordsRepository(String rootPath, ObjectMapper serializationUnit)
    {
        this.serializationUnit = serializationUnit;
        this.controller = new Controller(getRepositoryInfoMapper());
        this.rootPath = rootPath;
    }


    public RecordsRepository initIfNotExistsAndConnect() { if (!exists()) controller.initAndConnect(); else controller.connect(); return this; }
    public RecordsRepository initIfNotExists() { if (!exists()) controller.init(); return this; }
    public RecordsRepository connect() { controller.connect(); return this; }
    public RecordsRepository init() { controller.init(); return this; }


    public BigInteger makeRecord(Object record)
    {
        var id = allocateNewRecordId();

        try { serializationUnit.writeValue(new File(rootPath, getFileNameForId(id)), record); }
        catch (IOException e) { throw new RepositoryInternalException(e); }

        return id;
    }

    public <TRecord> TRecord getRecord(BigInteger id, Class<TRecord> type)
    {
        try { return serializationUnit.readValue(new File(rootPath, getFileNameForId(id)), type); }
        catch (IOException e) { throw new RepositoryInternalException(e); }
    }


    public void deleteRecord(BigInteger id)
    {
        if (!new File(rootPath, getFileNameForId(id)).delete()) throw new RepositoryInternalException();
    }

    public void clearAllRecords()
    {
        try
        {
            controller.lock.writeLock().lock();

            if (!Arrays.stream(Objects.requireNonNull(new File(rootPath).listFiles()))
                    .filter(file -> file.getName().endsWith(RECORD_EXTENSION))
                    .map(File::delete).reduce(true, (acc, res) -> acc && res))
                throw new RepositoryInternalException();

            controller.updateInfoUnsafe(new RepositoryInfo(controller.info).dropLastUsedId());
        }
        catch (NullPointerException ignored) {}
        catch (IOException e) { throw new RepositoryInternalException(e); }
        finally { controller.lock.writeLock().unlock(); }
    }


    protected String getFileNameForId(BigInteger id)
    {
        return String.format(RECORD_NAME_PATTERN, id.toString()) + RECORD_EXTENSION;
    }

    protected synchronized BigInteger allocateNewRecordId()
    {
        try
        {
            controller.lock.writeLock().lock();
            throwIfDisconnected();
            var updatedInfo = new RepositoryInfo(controller.info);
            var newId = updatedInfo.lastUsedId.add(BigInteger.ONE);
            updatedInfo.lastUsedId = newId;
            controller.updateInfoUnsafe(updatedInfo);
            return newId;
        }
        catch (IOException e)
        {
            throw new RepositoryInternalException(e);
        }
        finally
        {
            controller.lock.writeLock().unlock();
        }
    }


    protected class Controller
    {
        protected final ObjectMapper mapper;


        private RepositoryInfo info;
        private final ReadWriteLock lock;


        protected void updateInfoUnsafe(RepositoryInfo update) throws IOException { writeInfoUnsafe(update); info = update; }
        protected void writeInfoUnsafe(RepositoryInfo toWrite) throws IOException { mapper.writeValue(new File(rootPath, CONTROL_FILE_NAME), toWrite); }
        protected RepositoryInfo readInfoUnsafe() throws IOException { return mapper.readValue(new File(rootPath, CONTROL_FILE_NAME), RepositoryInfo.class); }


        public Controller(ObjectMapper mapper)
        {
            this.lock = new ReentrantReadWriteLock();
            this.mapper = mapper;
        }


        protected boolean exists()
        {
            lock.readLock().lock();
            var result = new File(rootPath, CONTROL_FILE_NAME).exists();
            lock.readLock().unlock();
            return result;
        }

        protected boolean connected()
        {
            lock.readLock().lock();
            var result = info != null;
            lock.readLock().unlock();
            return result;
        }


        protected void init()
        {
            try
            {
                lock.writeLock().lock();
                throwIfAlreadyConnected();
                writeInfoUnsafe(new RepositoryInfo());
            }
            catch (IOException e) { throw new RepositoryInternalException(e); }
            finally { lock.writeLock().unlock(); }
        }

        protected void initAndConnect()
        {
            var initial = new RepositoryInfo();
            try
            {
                lock.writeLock().lock();
                throwIfAlreadyConnected();
                writeInfoUnsafe(initial);
                info = initial;
            }
            catch (IOException e) { throw new RepositoryInternalException(e); }
            finally { lock.writeLock().unlock(); }
        }

        protected void connect()
        {
            try
            {
                lock.writeLock().lock();
                throwIfAlreadyConnected();
                info = readInfoUnsafe();
            }
            catch (IOException e) { throw new RepositoryInternalException(e); }
            finally { lock.writeLock().unlock(); }
        }
    }

    @JsonRootName("repositoryInfo")
    protected static class RepositoryInfo
    {
        @JsonProperty("lastUsedId") private BigInteger lastUsedId = BigInteger.ZERO;

        public BigInteger getLastUsedId() { return lastUsedId; }
        public RepositoryInfo setLastUsedId(BigInteger lastUsedId) { this.lastUsedId = lastUsedId; return this; }
        public RepositoryInfo dropLastUsedId() { return setLastUsedId(BigInteger.ZERO); }


        public RepositoryInfo() {}
        public RepositoryInfo(RepositoryInfo origin)
        {
            this.lastUsedId = origin.lastUsedId;
        }
    }
}
