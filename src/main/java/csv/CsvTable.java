package csv;

import java.util.*;

public class CsvTable {

    private final List<String> headers = new ArrayList<>();

    private final List<Map<String, String>> rows = new ArrayList<>();


    public List<String> getHeaders(){
        return Collections.unmodifiableList(headers);
    }

    public List<Map<String, String>> getRows(){
        return Collections.unmodifiableList(rows);
    }

    public void setHeaders(List<String> hdrs){
        headers.clear();
        headers.addAll(hdrs);
    }

    public void addRow(List<String> valores){
        Map<String, String> row = new LinkedHashMap<>();
        int n = headers.size();

        for (int i = 0; i < n; i++) {

            String key = headers.get(i);

            String value = (i < valores.size()) ? valores.get(i) : "";

            row.put(key, value);

        }
        rows.add(row);

    }
    public int size(){
        return rows.size();
    }


}
