package services;

import com.google.gson.*;
import model.animals.Animal;

import java.lang.reflect.Type;

public class AnimalAdapter implements JsonSerializer<Animal>, JsonDeserializer<Animal> {
    @Override
    public JsonElement serialize(Animal src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject result = new JsonObject();
        result.add("name", context.serialize(src, src.getClass()));
        result.add("weight", context.serialize(src, src.getClass()));
        result.add("animalType", context.serialize(src, src.getClass()));

        return result;
    }

    @Override
    public Animal deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String type = jsonObject.get("animalType").getAsString();
        JsonElement element = jsonObject.get("properties");

        try {
            return context.deserialize(element, Class.forName("model.animals." + type));
        } catch (ClassNotFoundException cnfe) {
            throw new JsonParseException("Unknown element type: " + type, cnfe);
        }
    }
}