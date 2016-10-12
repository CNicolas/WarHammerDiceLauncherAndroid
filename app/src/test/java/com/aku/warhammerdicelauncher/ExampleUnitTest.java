package com.aku.warhammerdicelauncher;

import com.aku.warhammerdicelauncher.utils.enums.Characteristic;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testCharacteristicEnumValue() {
        Characteristic actual = Characteristic.fromString("agIlIty");
        assertEquals(Characteristic.AGILITY, actual);
    }
}