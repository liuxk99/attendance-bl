package com.sj.attendance.bl;

import com.google.gson.Gson;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.FileReader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;

public class WorkTimePolicySetConfigTest {

    public static final String CONF_FILE = "conf.json";

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testcase_001() throws Exception {
        Gson gson = new Gson();

        WorkTimePolicySetConfig obj = new WorkTimePolicySetConfig();
        obj.generateDef();

        // 1. Java object to JSON file
        Writer writer = Files.newBufferedWriter(Paths.get(CONF_FILE));
        gson.toJson(obj, writer);
        writer.close();

        // 2. Java object to JSON string
        String jsonInString = gson.toJson(obj);

        System.out.println(jsonInString);

        // from JSON file
        FileReader fileReader = new FileReader(CONF_FILE);
        WorkTimePolicySetConfig config = gson.fromJson(fileReader, WorkTimePolicySetConfig.class);

        int policySetIndex = config.getPolicySetIndex();
        config.setPolicySet(config.getPolicySetList().get(policySetIndex));
        for (WorkTimePolicySet policySet : config.getPolicySetList()) {
            int policyIndex = policySet.getIndex();
            policySet.setPolicy(policySet.getPolicyList().get(policyIndex));
        }
        System.out.println("---");
        System.out.println(config);
        System.out.println("---");
    }
}