package oop.labs.lab4.math.eval;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import java.util.*;

@JsonRootName("solutionNode")
@SuppressWarnings({"unused", "UnusedReturnValue"})
public final class SolutionNode implements List<SolutionNode>
{
    @JsonProperty("comment") private final String comment;
    @JsonProperty("content") private final Object content;

    @JsonProperty("subNodes") private final List<SolutionNode> subNodes;


    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof SolutionNode that)) return false;
        return Objects.equals(comment, that.comment) && Objects.equals(content, that.content) && subNodes.equals(that.subNodes);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(comment, content, subNodes);
    }


    @JsonCreator
    private SolutionNode(@JsonProperty("comment") String comment,
                         @JsonProperty("content") Object content,
                         @JsonProperty("subNodes") List<SolutionNode> subNodes)
    {
        this.subNodes = subNodes;
        this.comment = comment;
        this.content = content;
    }

    public SolutionNode() { this(null, null, new ArrayList<>()); }
    public SolutionNode(int capacity) { this(null, null, new ArrayList<>(capacity)); }
    public SolutionNode(String comment, Object content) { this(comment, content, new ArrayList<>()); }
    public SolutionNode(String comment, Object content, int capacity) { this(comment, content, new ArrayList<>(capacity)); }


    public String comment() { return comment; }
    public Object content() { return content; }

    public int nodesCount() { return subNodes.size(); }
    @Override public int size() { return nodesCount(); }
    @Override public boolean isEmpty() { return subNodes.isEmpty(); }

    @Override public Object[] toArray() { return subNodes.toArray(); }
    @Override @SuppressWarnings("SuspiciousToArrayCall") public <T> T[] toArray(T[] a) { return subNodes.toArray(a); }

    public boolean containsNode(SolutionNode node) { return subNodes.contains(node); }
    @Override public boolean contains(Object o) { return subNodes.contains(o); }

    public boolean containsAllNodes(Collection<? extends SolutionNode> nodes) { return new HashSet<>(subNodes).containsAll(nodes); }
    @Override public boolean containsAll(Collection<?> c) { return new HashSet<>(subNodes).containsAll(c); }

    public SolutionNode getNode(int index) { return subNodes.get(index); }
    @Override public SolutionNode get(int index) { return getNode(index); }

    public SolutionNode setNode(int index, SolutionNode node) { return subNodes.set(index, node); }
    @Override public SolutionNode set(int index, SolutionNode element) { return setNode(index, element); }

    public void clearNodes() { subNodes.clear(); }
    @Override public void clear() { clearNodes(); }

    public boolean removeNode(SolutionNode node) { return subNodes.remove(node); }
    @Override public boolean remove(Object o) { return subNodes.remove(o); }

    public SolutionNode removeNode(int index) { return subNodes.remove(index); }
    @Override public SolutionNode remove(int index) { return removeNode(index); }

    public boolean removeAllNodes(Collection<? extends SolutionNode> nodes) { return subNodes.removeAll(nodes); }
    @Override public boolean removeAll(Collection<?> c) { return subNodes.removeAll(c); }

    public boolean retainAllNodes(Collection<? extends SolutionNode> nodes) { return subNodes.retainAll(nodes); }
    @Override public boolean retainAll(Collection<?> c) { return subNodes.retainAll(c); }

    public void addNode(int index, SolutionNode node) { subNodes.addAll(index, node); }
    @Override public void add(int index, SolutionNode element) { addNode(index, element); }

    public boolean newNode(String comment, Object content, int capacity) { return addNode(new SolutionNode(comment, content, capacity)); }
    public boolean newNode(String comment, Object content) { return addNode(new SolutionNode(comment, content)); }
    public boolean newFinalNode(String comment, Object content) { return newNode(comment, content, 0); }

    public boolean addNode(SolutionNode node) { return subNodes.add(node); }
    @Override public boolean add(SolutionNode element) { return addNode(element); }

    public boolean addAllNodes(Collection<? extends SolutionNode> nodes) { return subNodes.addAll(nodes); }
    @Override public boolean addAll(Collection<? extends SolutionNode> c) { return addAllNodes(c); }

    public boolean addAllNodes(int index, Collection<? extends SolutionNode> nodes) { return subNodes.addAll(index, nodes); }
    @Override public boolean addAll(int index, Collection<? extends SolutionNode> c) { return addAllNodes(index, c); }

    @Override public int indexOf(Object o) { return subNodes.indexOf(o); }
    @Override public int lastIndexOf(Object o) { return subNodes.lastIndexOf(o); }

    @Override public List<SolutionNode> subList(int fromIndex, int toIndex) { return subNodes.subList(fromIndex, toIndex); }

    @Override public Iterator<SolutionNode> iterator() { return subNodes.iterator(); }
    @Override public ListIterator<SolutionNode> listIterator() { return subNodes.listIterator(); }
    @Override public ListIterator<SolutionNode> listIterator(int index) { return subNodes.listIterator(index); }
}
