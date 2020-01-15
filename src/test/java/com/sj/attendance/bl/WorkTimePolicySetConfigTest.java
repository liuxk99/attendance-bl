package com.sj.attendance.bl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
        Gson gsonTo = new Gson();

        WorkTimePolicySetConfig obj = new WorkTimePolicySetConfig();
        obj.generateDef();

        // serialization
        String json = gsonTo.toJson(obj);
        System.out.println(json);

        // deserialization
        PolicyDeserializerAdapter deserializer = new PolicyDeserializerAdapter(FixWorkTimePolicy.TAG);

        // registering each Type into the Deserializer's HashMap (key-value pair),
        // where the key (String) must be carried by the object (you can find it in the BaseClass,
        // called "clazz")
        deserializer.registerClassType(FlexWorkTimePolicy.class.getSimpleName(), FlexWorkTimePolicy.class);
        deserializer.registerClassType(FixWorkTimePolicy.class.getSimpleName(), FixWorkTimePolicy.class);
        Gson gsonFrom = new GsonBuilder().registerTypeAdapter(FixWorkTimePolicy.class, deserializer).create();

        WorkTimePolicySetConfig config = gsonFrom.fromJson(json, WorkTimePolicySetConfig.class);

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

    @Test
    public void testcase_002() throws Exception {
        Gson gsonTo = new Gson();

        WorkTimePolicySetConfig obj = new WorkTimePolicySetConfig();
        obj.generateDef();

        Writer writer = Files.newBufferedWriter(Paths.get(CONF_FILE));
        gsonTo.toJson(obj, writer);
        writer.close();

        PolicyDeserializerAdapter deserializer = new PolicyDeserializerAdapter(FixWorkTimePolicy.TAG);

        // registering each Type into the Deserializer's HashMap (key-value pair),
        // where the key (String) must be carried by the object (you can find it in the BaseClass,
        // called "clazz")
        deserializer.registerClassType(FlexWorkTimePolicy.class.getSimpleName(), FlexWorkTimePolicy.class);
        deserializer.registerClassType(FixWorkTimePolicy.class.getSimpleName(), FixWorkTimePolicy.class);
        Gson gsonFrom = new GsonBuilder().registerTypeAdapter(FixWorkTimePolicy.class, deserializer).create();

        // from JSON file
        FileReader fileReader = new FileReader(CONF_FILE);
        WorkTimePolicySetConfig config = gsonFrom.fromJson(fileReader, WorkTimePolicySetConfig.class);

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