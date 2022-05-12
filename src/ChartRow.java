import java.util.Objects;

public class ChartRow {

   final Rule rule;
    final int start, end, dot;

    int count = 0;

    ChartRow(Rule rule, int start, int end, int dot, int count)
    {
        this.rule = rule; this.start=start; this.end = end;  this.dot= dot; this.count = count;
    }

    boolean complete()
    {
        return dot == rule.rhs.size();
    }

    String onRight ()
    {
        if (complete()) throw new UnsupportedOperationException("nothing");

        return rule.rhs.get(dot);


    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChartRow chartRow = (ChartRow) o;
        return start == chartRow.start &&
                end == chartRow.end &&
                dot == chartRow.dot &&
                Objects.equals(rule, chartRow.rule);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rule, start, end, dot);
    }

    @Override
    public String toString() {
        String res =  rule.lhs +" -> ";

        for (int i=0;i<rule.rhs.size();i++)
        {
            if (i==dot) res+= ". ";
            res+= rule.rhs.get(i)+" ";
        }

        res+= "["+start+","+end+"]";
        return  res;
    }
}
