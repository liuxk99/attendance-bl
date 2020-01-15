package com.sj.attendance.bl;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class PolicyDeserializerAdapter implements JsonDeserializer<FixWorkTimePolicy> {
    private String typeName;
    private Gson gson;
    private Map<String, Class<? extends FixWorkTimePolicy>> classTypeRegistry;

    public PolicyDeserializerAdapter(String typeName) {
        this.typeName = typeName;
        gson = new Gson();
        classTypeRegistry = new HashMap<>();
    }

    public void registerClassType(String classTypeName, Class<? extends FixWorkTimePolicy> classType) {
        // registering Types to Strings
        classTypeRegistry.put(classTypeName, classType);
    }

    @Override
    public FixWorkTimePolicy deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        JsonElement typeElement = jsonObject.get(typeName);
        String clazz = typeElement.getAsString();
        Class<? extends FixWorkTimePolicy> classType = classTypeRegistry.get(clazz);
        return gson.fromJson(json, classType);
    }
}
