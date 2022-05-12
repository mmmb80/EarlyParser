import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Rule {

    static List<Rule> grammar;

    static void init()
    {

        grammar = Arrays.asList(
                new Rule("S", Arrays.asList("NP", "VP")),
                new Rule("NP", Arrays.asList("N", "PP")),
                new Rule("NP", Arrays.asList("N")),
                new Rule("PP", Arrays.asList("P", "NP")),
                new Rule("VP", Arrays.asList("VP", "PP")),
                new Rule("VP", Arrays.asList("V", "VP")),
                new Rule("VP", Arrays.asList("V", "NP")),
                new Rule("VP", Arrays.asList("V")),
                new Rule("N", Arrays.asList("they")),
                new Rule("N", Arrays.asList("December")),
                new Rule("N", Arrays.asList("can")),
                new Rule("N", Arrays.asList("fish")),
                new Rule("N", Arrays.asList("rivers")),
                new Rule("P", Arrays.asList("in")),
                new Rule("V", Arrays.asList("can")),
                new Rule("V", Arrays.asList("fish"))

        );
    }

    Rule(String lhs, List<String> rhs)
    {
        this.lhs = lhs;
        this.rhs= rhs;

    }

    String lhs;
    List<String> rhs;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rule rule = (Rule) o;
        return Objects.equals(lhs, rule.lhs) &&
                Objects.equals(rhs, rule.rhs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lhs, rhs);
    }
}
