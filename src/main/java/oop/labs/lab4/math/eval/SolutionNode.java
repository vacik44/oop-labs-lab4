package oop.labs.lab4.math.eval;

import com.fasterxml.jackson.annotation.*;

import java.util.*;

@SuppressWarnings("unused")
@JsonTypeName("solutionNode")
@JsonIncludeProperties({"comment", "subNodes", "content"})
public final class SolutionNode
{
    @JsonProperty("comment") private final String comment;
    @JsonProperty("subNodes") private final List<SolutionNode> subNodes;


    @JsonProperty("content")
    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property="@type")
    private final Object content;


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
    public boolean isEmpty() { return subNodes.isEmpty(); }

    public SolutionNode getNode(int index) { return subNodes.get(index); }
    public List<SolutionNode> listNodes() { return Collections.unmodifiableList(subNodes); }

    public void newNode(String comment, Object content, int capacity) { addNode(new SolutionNode(comment, content, capacity)); }
    public void newNode(String comment, Object content) { addNode(new SolutionNode(comment, content)); }
    public void newFinalNode(String comment, Object content) { newNode(comment, content, 0); }

    public void addNode(int index, SolutionNode node) { subNodes.add(index, node); }
    public void addNode(SolutionNode node) { subNodes.add(node); }
}
