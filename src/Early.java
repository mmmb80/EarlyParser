import java.util.*;

public class Early {




    void parse (List<String> input)
    {

        Rule.init();

        int count = 0;

        List<ChartRow> chart = new ArrayList<>();
        HashSet<ChartRow> derived = new HashSet<>();
        Queue<ChartRow> toAdd = new ArrayDeque<>();

        //init
        ChartRow current = new ChartRow(Rule.grammar.get(0), 0, 0, 0,1);
        toAdd.add(current);


        boolean done = false;

        ChartRow last = null;




        while (true)
        {

            if (toAdd.isEmpty())
            {
                System.out.println( ((done)?last.count:0) + " parses.");
                break;
            }

            current = toAdd.remove();

            if (derived.contains(current))
            {
                for (ChartRow row : chart)
                {
                    if (row.equals(current) && row.start!=row.end)
                    {

                        row.count+= current.count;
                    }
                }
                continue;
            }
            chart.add(current);
            derived.add(current);

            System.out.println(current);


            if (current.start ==0 && current.end == input.size() && current.dot == current.rule.rhs.size() && current.rule.equals(chart.get(0).rule))
            {
                done = true;
                last = current;

            }




            if (!current.complete())
            {
                String symbol = current.rule.rhs.get(current.dot);
                //scan


               if (current.end!= input.size() && symbol.equals(input.get(current.end))) toAdd.add(new ChartRow(current.rule, current.start, current.end+1, 1+current.dot , current.count));

                //predict
               for (Rule rule: Rule.grammar)
               {
                    if (rule.lhs.equals(symbol)) toAdd.add(new ChartRow(rule,current.end,current.end,0,1));
               }

            }


            //complete
            for (ChartRow row : chart)
            {


                if (current.complete() && !row.complete() && current.start == row.end && current.rule.lhs.equals(row.onRight()))
                    toAdd.add(new ChartRow(row.rule, row.start, current.end, row.dot+1, row.count*current.count));

                if (row.complete() && !current.complete() && row.start == current.end && row.rule.lhs.equals(current.onRight()))
                    toAdd.add(new ChartRow(current.rule, current.start, row.end, current.dot+1, row.count*current.count));
            }





        }

    }

}
