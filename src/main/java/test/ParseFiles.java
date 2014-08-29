package test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import test.domain.DimTableByDimType;
import test.domain.Record;
import test.repository.DimTableByDimTypeRepository;
import test.repository.RecordRepository;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yar on 18.08.14.
 */
@Component
public class ParseFiles {

//    private String file1 = "/home/yar/Java/DIOPR/rxp_gold_tranx_pb_100";
//    private String file2 = "/home/yar/Java/DIOPR/sp_gls_gt_tranx_all_asof_100";
    private String file1 = "/home/yar/Java/DIOPR/rxp_gold_tranx_pb";
    private String file2 = "/home/yar/Java/DIOPR/sp_gls_gt_tranx_all_asof";
    private BufferedReader bufferedReader;
    private String client = "GT";
    private String broker = "GOLD";
    private String separator1 = ",";
    private String separator2 = "~";

    public ParseFiles() {
    }

    @Autowired
    private RecordRepository recordRepository;

//    @Autowired
//    private DimTableByDimTypeRepository dimTableByDimTypeRepository;

//    @PostConstruct
    public void start() throws IOException {
        parse(file1, separator1);
        parse(file2, separator2);
    }

    public void parse(String file, String separator) throws IOException {
        System.out.println(file);
        bufferedReader = new BufferedReader(new FileReader(file));
        String[] fields = bufferedReader.readLine().split(separator);
        String line = bufferedReader.readLine();
        String[] values = null;
        List<Record> records = new ArrayList<Record>();
        int counter = 0;
        while (line != null) {
            values = line.split(separator);
            Map<String, String> fieldsMap = new HashMap<String, String>();
            for (int i = 0; i < fields.length; i++) {
                if (values.length > i) {
                    fieldsMap.put(fields[i].toLowerCase(), values[i]);
                } else {
                    fieldsMap.put(fields[i].toLowerCase(), "");
                }
            }
            records.add(new Record(broker, client, fieldsMap, "123"));
            if (counter == 1000) {
                recordRepository.save(records);
                records.clear();
                System.out.println(counter);
                counter = 0;
            }
            line = bufferedReader.readLine();
            counter++;
        }
        if (records.size() != 0) {
            recordRepository.save(records);
        }
        bufferedReader.close();
    }
}