package com.redhat.demo;

import io.quarkus.test.junit.NativeImageTest;

@NativeImageTest
public class NativeSlashResourceIT extends SlashResourceTest {

    // Execute the same tests but in native mode.
}