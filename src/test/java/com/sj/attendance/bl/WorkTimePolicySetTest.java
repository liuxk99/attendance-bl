package com.sj.attendance.bl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import junit.framework.AssertionFailedError;

import org.junit.Test;

import java.io.FileReader;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static com.sj.attendance.bl.WorkTimePolicyFactory.createWorkTimePolicySetFixWorkTime;
import static com.sj.attendance.bl.WorkTimePolicyFactory.createWorkTimePolicySetFlexWorkTime;
import static com.sj.attendance.bl.WorkTimePolicyFactory.createWorkTimePolicySetList;
import static org.junit.Assert.assertEquals;

public class WorkTimePolicySetTest {
    private final String JSON_FILE = "WorkTimePolicySetTest.json";

    @Test
    public void testcase_FixWorkTimePolicySet() {
        WorkTimePolicySet workTimePolicySet = createWorkTimePolicySetFixWorkTime();
        System.out.println(workTimePolicySet.toString());
    }

    @Test
    public void testcase_FlexWorkTimePolicySet() {
        WorkTimePolicySet workTimePolicySet = createWorkTimePolicySetFlexWorkTime();
        System.out.println(workTimePolicySet.toString());
    }

    @Test
    public void testcase_PolicySet() {
        List<WorkTimePolicySet> workTimePolicySetList = createWorkTimePolicySetList();
        for (WorkTimePolicySet policySet : workTimePolicySetList) {
            System.out.println("=>---");
            System.out.print(policySet);
            System.out.println("<----");
        }
    }

    @Test
    public void testcase_001() throws Exception {
        WorkTimePolicySet source = createWorkTimePolicySetFixWorkTime();
        WorkTimePolicySet target = null;

        // serialization
        {
            Gson gsonTo = new Gson();
            Writer writer = Files.newBufferedWriter(Paths.get(JSON_FILE));
            gsonTo.toJson(source, writer);
            writer.close();
        }

        // deserialization
        {
            PolicyDeserializerAdapter deserializer = new PolicyDeserializerAdapter(FixWorkTimePolicy.TAG);

            // registering each Type into the Deserializer's HashMap (key-value pair),
            // where the key (String) must be carried by the object (you can find it in the BaseClass,
            // called "clazz")
            deserializer.registerClassType(FlexWorkTimePolicy.class.getSimpleName(), FlexWorkTimePolicy.class);
            deserializer.registerClassType(FixWorkTimePolicy.class.getSimpleName(), FixWorkTimePolicy.class);
            Gson gsonFrom = new GsonBuilder().registerTypeAdapter(FixWorkTimePolicy.class, deserializer).create();

            // from JSON file
            FileReader fileReader = new FileReader(JSON_FILE);
            target = gsonFrom.fromJson(fileReader, WorkTimePolicySet.class);
        }

        // comparing
        assertEquals(source, target);
    }

    @Test
    public void testcase_002() throws Exception {
        List<WorkTimePolicySet> source = createWorkTimePolicySetList();
        List<WorkTimePolicySet> target;

        // serialization
        {
            Gson gsonTo = new Gson();
            Writer writer = Files.newBufferedWriter(Paths.get(JSON_FILE));
            gsonTo.toJson(source, writer);
            writer.close();
        }

        // deserialization
        {
            PolicyDeserializerAdapter deserializer = new PolicyDeserializerAdapter(FixWorkTimePolicy.TAG);

            // registering each Type into the Deserializer's HashMap (key-value pair),
            // where the key (String) must be carried by the object (you can find it in the BaseClass,
            // called "clazz")
            deserializer.registerClassType(FlexWorkTimePolicy.class.getSimpleName(), FlexWorkTimePolicy.class);
            deserializer.registerClassType(FixWorkTimePolicy.class.getSimpleName(), FixWorkTimePolicy.class);
            Gson gsonFrom = new GsonBuilder().registerTypeAdapter(FixWorkTimePolicy.class, deserializer).create();

            // from JSON file
            FileReader fileReader = new FileReader(JSON_FILE);
            // Uncheked assignment from capture<? extends java.util.List> to java.util.List<WorkTimePolicySet>.
            // target = gsonFrom.fromJson(fileReader, source.getClass());

            WorkTimePolicySet[] policySetArray = gsonFrom.fromJson(fileReader, WorkTimePolicySet[].class);
            target = Arrays.asList(policySetArray);
        }

        // comparing
        assertEquals(source, target);
    }
}