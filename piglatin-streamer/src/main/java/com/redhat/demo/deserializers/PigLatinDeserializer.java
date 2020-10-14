package com.redhat.demo.deserializers;

import io.quarkus.kafka.client.serialization.JsonbDeserializer;

import com.redhat.demo.PigLatin;

public class PigLatinDeserializer extends JsonbDeserializer<PigLatin>{

    public PigLatinDeserializer(){
        super(PigLatin.class);
    }
}