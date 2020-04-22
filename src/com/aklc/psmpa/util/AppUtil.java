package com.aklc.psmpa.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class AppUtil {

	public static String generatePatientID() {
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyhhmmss");
		return "PTNT" + sdf.format(new Timestamp(System.currentTimeMillis()));
	}

	public static String generateVisitID() {
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyhhmmss");
		return "VIST" + sdf.format(new Timestamp(System.currentTimeMillis()));
	}

}
