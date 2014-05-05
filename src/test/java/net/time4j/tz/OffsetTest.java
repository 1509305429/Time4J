package net.time4j.tz;

import java.math.BigDecimal;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


@RunWith(JUnit4.class)
public class OffsetTest {

    @Test
    public void atLongitudeBigDecimalMinus14_001() {
        ZonalOffset offset = ZonalOffset.atLongitude(new BigDecimal("-14.001"));
        assertThat(
            offset.getIntegralAmount(),
            is(-3360));
        assertThat(
            offset.getFractionalAmount(),
            is(-240000000));
    }

    @Test
    public void atLongitudeBigDecimalMinus0_001() {
        ZonalOffset offset = ZonalOffset.atLongitude(new BigDecimal("-0.001"));
        assertThat(
            offset.getIntegralAmount(),
            is(0));
        assertThat(
            offset.getFractionalAmount(),
            is(-240000000));
    }

    @Test
    public void atLongitudeBigDecimalMinus14() {
        ZonalOffset offset = ZonalOffset.atLongitude(new BigDecimal("-14"));
        assertThat(
            offset.getIntegralAmount(),
            is(-3360));
        assertThat(
            offset.getFractionalAmount(),
            is(0));
    }

    @Test
    public void atLongitudeBigDecimalPlus15() {
        ZonalOffset offset = ZonalOffset.atLongitude(new BigDecimal("15"));
        assertThat(
            offset.getIntegralAmount(),
            is(3600));
        assertThat(
            offset.getFractionalAmount(),
            is(0));
    }

    @Test(expected=IllegalArgumentException.class)
    public void atLongitudeBigDecimalPlus180_001() {
        ZonalOffset.atLongitude(new BigDecimal("180.001"));
    }

    @Test
    public void atLongitudeArcus() {
        ZonalOffset offset =
            ZonalOffset.atLongitude(ZonalOffset.Sign.BEHIND_UTC, 14, 30, 45);
        assertThat(
            offset.getIntegralAmount(),
            is(-3483)); // -14.5125 degrees
        assertThat(
            offset.getFractionalAmount(),
            is(0));
    }

    @Test(expected=IllegalArgumentException.class)
    public void atLongitudeArcusOutOfRange1() {
        ZonalOffset.atLongitude(ZonalOffset.Sign.BEHIND_UTC, 14, 60, 45);
    }

    @Test(expected=IllegalArgumentException.class)
    public void atLongitudeArcusOutOfRange2() {
        ZonalOffset.atLongitude(ZonalOffset.Sign.BEHIND_UTC, 14, 30, 60);
    }

    @Test(expected=IllegalArgumentException.class)
    public void atLongitudeArcusOutOfRange3() {
        ZonalOffset.atLongitude(ZonalOffset.Sign.BEHIND_UTC, 180, 0, 1);
    }

    @Test
    public void testEquals() {
        assertThat(
            ZonalOffset.atLongitude(new BigDecimal("-14.001")),
            is(ZonalOffset.ofTotalSeconds(-3360, -240000000)));
    }

    @Test
    public void testHashCode() {
        ZonalOffset offset1 =
            ZonalOffset.atLongitude(new BigDecimal("-14.001"));
        ZonalOffset offset2 =
            ZonalOffset.ofTotalSeconds(-3360, -240000000);
        assertThat(
            offset1.hashCode() == offset2.hashCode(),
            is(true));
    }

    @Test
    public void ofTotalSeconds() {
        ZonalOffset offset =
            ZonalOffset.ofTotalSeconds(-3360);
        assertThat(
            offset.getIntegralAmount(),
            is(-3360));
        assertThat(
            offset.getFractionalAmount(),
            is(0));
    }

    @Test
    public void ofTotalSecondsWithFraction() {
        ZonalOffset offset =
            ZonalOffset.ofTotalSeconds(-3360, -240000000);
        assertThat(
            offset.getIntegralAmount(),
            is(-3360));
         assertThat(
            offset.getFractionalAmount(),
            is(-240000000));
    }

    @Test(expected=IllegalArgumentException.class)
    public void ofTotalSecondsWithDifferentSigns() {
        ZonalOffset.ofTotalSeconds(-3360, 240000000);
    }

    @Test(expected=IllegalArgumentException.class)
    public void ofTotalSecondsOutOfRange1() {
        ZonalOffset.ofTotalSeconds(18 * 3600 + 1);
    }

    @Test(expected=IllegalArgumentException.class)
    public void ofTotalSecondsOutOfRange2() {
        ZonalOffset.ofTotalSeconds(-18 * 3600, -1);
    }

    @Test
    public void getIntegralAmount() {
        assertThat(
            ZonalOffset.ofTotalSeconds(7200, 671).getIntegralAmount(),
            is(7200));
    }

    @Test
    public void getFractionalAmount() {
         assertThat(
            ZonalOffset.ofTotalSeconds(7200, 671).getFractionalAmount(),
            is(671));
    }

    @Test
    public void getAbsoluteHours() {
         assertThat(
            ZonalOffset.ofTotalSeconds(-7200, -671).getAbsoluteHours(),
            is(2));
    }

    @Test
    public void getAbsoluteMinutes() {
         assertThat(
            ZonalOffset.ofTotalSeconds(-9000, -671).getAbsoluteMinutes(),
            is(30));
    }

    @Test
    public void getAbsoluteSeconds() {
         assertThat(
            ZonalOffset.ofTotalSeconds(-7245, -671).getAbsoluteSeconds(),
            is(45));
    }

    @Test
    public void getSign() {
         assertThat(
            ZonalOffset.ofTotalSeconds(-7245).getSign(),
            is(ZonalOffset.Sign.BEHIND_UTC));
         assertThat(
            ZonalOffset.ofTotalSeconds(7245).getSign(),
            is(ZonalOffset.Sign.AHEAD_OF_UTC));
    }

    @Test
    public void ofHours() {
        ZonalOffset offset =
            ZonalOffset.of(ZonalOffset.Sign.BEHIND_UTC, 5);
        assertThat(
            offset.getAbsoluteHours(),
            is(5));
        assertThat(
            offset.getAbsoluteMinutes(),
            is(0));
        assertThat(
            offset.getAbsoluteSeconds(),
            is(0));
        assertThat(
            offset.getSign(),
            is(ZonalOffset.Sign.BEHIND_UTC));
    }

    @Test
    public void ofHoursMinutes() {
        ZonalOffset offset =
            ZonalOffset.of(ZonalOffset.Sign.BEHIND_UTC, 5, 30);
        assertThat(
            offset.getAbsoluteHours(),
            is(5));
        assertThat(
            offset.getAbsoluteMinutes(),
            is(30));
        assertThat(
            offset.getAbsoluteSeconds(),
            is(0));
        assertThat(
            offset.getSign(),
            is(ZonalOffset.Sign.BEHIND_UTC));
    }

    @Test
    public void ofHoursMinutesSeconds() {
        ZonalOffset offset =
            ZonalOffset.of(ZonalOffset.Sign.BEHIND_UTC, 5, 30, 45);
        assertThat(
            offset.getAbsoluteHours(),
            is(5));
        assertThat(
            offset.getAbsoluteMinutes(),
            is(30));
        assertThat(
            offset.getAbsoluteSeconds(),
            is(45));
        assertThat(
            offset.getSign(),
            is(ZonalOffset.Sign.BEHIND_UTC));
    }

    @Test(expected=IllegalArgumentException.class)
    public void ofHoursMinutesWithDifferentSigns() {
        ZonalOffset.of(ZonalOffset.Sign.BEHIND_UTC, -9, 15);
    }

    @Test(expected=IllegalArgumentException.class)
    public void ofHoursMinutesOutOfRange() {
        ZonalOffset.of(ZonalOffset.Sign.BEHIND_UTC, 18, 1);
    }

    @Test
    public void compareTo() {
        ZonalOffset offset1 =
            ZonalOffset.of(ZonalOffset.Sign.BEHIND_UTC, 4);
        ZonalOffset offset2 =
            ZonalOffset.of(ZonalOffset.Sign.AHEAD_OF_UTC, 3);
        assertThat(
            offset1.compareTo(offset2) < 0,
            is(true));
        assertThat(
            offset2.compareTo(ZonalOffset.UTC) > 0,
            is(true));
    }

    @Test
    public void canonical() {
        assertThat(
            ZonalOffset.ofTotalSeconds(0).canonical(),
            is("Z"));
        assertThat(
            ZonalOffset.of(ZonalOffset.Sign.AHEAD_OF_UTC, 2, 15).canonical(),
            is("UTC+02:15"));
        assertThat(
            ZonalOffset.of(ZonalOffset.Sign.BEHIND_UTC, 5, 30, 45).canonical(),
            is("UTC-05:30:45"));
        assertThat(
            ZonalOffset.ofTotalSeconds(3600, 500000000).canonical(),
            is("UTC+01:00:00.500000000"));
    }

    @Test
    public void testToString() {
        assertThat(
            ZonalOffset.UTC.toString(),
            is("+00:00"));
        assertThat(
            ZonalOffset.of(ZonalOffset.Sign.AHEAD_OF_UTC, 2, 15).toString(),
            is("+02:15"));
        assertThat(
            ZonalOffset.of(ZonalOffset.Sign.BEHIND_UTC, 5, 30, 45).toString(),
            is("-05:30:45"));
        assertThat(
            ZonalOffset.ofTotalSeconds(3600, 500000000).toString(),
            is("+01:00:00.500000000"));
    }

    @Test
    public void constantUTC() {
        assertThat(
            ZonalOffset.UTC,
            is(ZonalOffset.ofTotalSeconds(0, 0)));
    }

}