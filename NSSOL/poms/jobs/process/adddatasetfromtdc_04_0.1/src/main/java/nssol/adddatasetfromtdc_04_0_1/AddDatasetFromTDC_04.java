
package nssol.adddatasetfromtdc_04_0_1;

import routines.DataOperation;
import routines.TalendDataGenerator;
import routines.DataQuality;
import routines.Relational;
import routines.Mathematical;
import routines.DataQualityDependencies;
import routines.SQLike;
import routines.Numeric;
import routines.TalendStringUtil;
import routines.TalendString;
import routines.StringHandling;
import routines.DQTechnical;
import routines.TalendDate;
import routines.DataMasking;
import routines.DqStringHandling;
import routines.system.*;
import routines.system.api.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.math.BigDecimal;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.util.Comparator;

@SuppressWarnings("unused")

/**
 * Job: AddDatasetFromTDC_04 Purpose: <br>
 * Description: <br>
 * 
 * @author tanaka, tanaka
 * @version 7.3.1.20200219_1130
 * @status
 */
public class AddDatasetFromTDC_04 implements TalendJob {
	static {
		System.setProperty("TalendJob.log", "AddDatasetFromTDC_04.log");
	}

	private static org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager
			.getLogger(AddDatasetFromTDC_04.class);

	protected static void logIgnoredError(String message, Throwable cause) {
		log.error(message, cause);

	}

	public final Object obj = new Object();

	// for transmiting parameters purpose
	private Object valueObject = null;

	public Object getValueObject() {
		return this.valueObject;
	}

	public void setValueObject(Object valueObject) {
		this.valueObject = valueObject;
	}

	private final static String defaultCharset = java.nio.charset.Charset.defaultCharset().name();

	private final static String utf8Charset = "UTF-8";

	// contains type for every context property
	public class PropertiesWithType extends java.util.Properties {
		private static final long serialVersionUID = 1L;
		private java.util.Map<String, String> propertyTypes = new java.util.HashMap<>();

		public PropertiesWithType(java.util.Properties properties) {
			super(properties);
		}

		public PropertiesWithType() {
			super();
		}

		public void setContextType(String key, String type) {
			propertyTypes.put(key, type);
		}

		public String getContextType(String key) {
			return propertyTypes.get(key);
		}
	}

	// create and load default properties
	private java.util.Properties defaultProps = new java.util.Properties();

	// create application properties with default
	public class ContextProperties extends PropertiesWithType {

		private static final long serialVersionUID = 1L;

		public ContextProperties(java.util.Properties properties) {
			super(properties);
		}

		public ContextProperties() {
			super();
		}

		public void synchronizeContext() {

			if (datasetName != null) {

				this.setProperty("datasetName", datasetName.toString());

			}

			if (projectName != null) {

				this.setProperty("projectName", projectName.toString());

			}

			if (tcToken != null) {

				this.setProperty("tcToken", tcToken.toString());

			}

			if (tdcPassword != null) {

				this.setProperty("tdcPassword", tdcPassword.toString());

			}

			if (tdcRootObjectId != null) {

				this.setProperty("tdcRootObjectId", tdcRootObjectId.toString());

			}

			if (tdcTargetObjectPath != null) {

				this.setProperty("tdcTargetObjectPath", tdcTargetObjectPath.toString());

			}

			if (tdcTargetSchema != null) {

				this.setProperty("tdcTargetSchema", tdcTargetSchema.toString());

			}

			if (tdcURL != null) {

				this.setProperty("tdcURL", tdcURL.toString());

			}

			if (tdcUser != null) {

				this.setProperty("tdcUser", tdcUser.toString());

			}

			if (tdpDatasetName != null) {

				this.setProperty("tdpDatasetName", tdpDatasetName.toString());

			}

			if (tdpId != null) {

				this.setProperty("tdpId", tdpId.toString());

			}

			if (tdpURL != null) {

				this.setProperty("tdpURL", tdpURL.toString());

			}

			if (workingDirectoryPath != null) {

				this.setProperty("workingDirectoryPath", workingDirectoryPath.toString());

			}

		}

		public String datasetName;

		public String getDatasetName() {
			return this.datasetName;
		}

		public String projectName;

		public String getProjectName() {
			return this.projectName;
		}

		public String tcToken;

		public String getTcToken() {
			return this.tcToken;
		}

		public java.lang.String tdcPassword;

		public java.lang.String getTdcPassword() {
			return this.tdcPassword;
		}

		public String tdcRootObjectId;

		public String getTdcRootObjectId() {
			return this.tdcRootObjectId;
		}

		public String tdcTargetObjectPath;

		public String getTdcTargetObjectPath() {
			return this.tdcTargetObjectPath;
		}

		public String tdcTargetSchema;

		public String getTdcTargetSchema() {
			return this.tdcTargetSchema;
		}

		public String tdcURL;

		public String getTdcURL() {
			return this.tdcURL;
		}

		public String tdcUser;

		public String getTdcUser() {
			return this.tdcUser;
		}

		public String tdpDatasetName;

		public String getTdpDatasetName() {
			return this.tdpDatasetName;
		}

		public String tdpId;

		public String getTdpId() {
			return this.tdpId;
		}

		public String tdpURL;

		public String getTdpURL() {
			return this.tdpURL;
		}

		public String workingDirectoryPath;

		public String getWorkingDirectoryPath() {
			return this.workingDirectoryPath;
		}

	}

	protected ContextProperties context = new ContextProperties(); // will be instanciated by MS.

	public ContextProperties getContext() {
		return this.context;
	}

	private final String jobVersion = "0.1";
	private final String jobName = "AddDatasetFromTDC_04";
	private final String projectName = "NSSOL";
	public Integer errorCode = null;
	private String currentComponent = "";

	private final java.util.Map<String, Object> globalMap = new java.util.HashMap<String, Object>();
	private final static java.util.Map<String, Object> junitGlobalMap = new java.util.HashMap<String, Object>();

	private final java.util.Map<String, Long> start_Hash = new java.util.HashMap<String, Long>();
	private final java.util.Map<String, Long> end_Hash = new java.util.HashMap<String, Long>();
	private final java.util.Map<String, Boolean> ok_Hash = new java.util.HashMap<String, Boolean>();
	public final java.util.List<String[]> globalBuffer = new java.util.ArrayList<String[]>();

	private RunStat runStat = new RunStat();

	// OSGi DataSource
	private final static String KEY_DB_DATASOURCES = "KEY_DB_DATASOURCES";

	private final static String KEY_DB_DATASOURCES_RAW = "KEY_DB_DATASOURCES_RAW";

	public void setDataSources(java.util.Map<String, javax.sql.DataSource> dataSources) {
		java.util.Map<String, routines.system.TalendDataSource> talendDataSources = new java.util.HashMap<String, routines.system.TalendDataSource>();
		for (java.util.Map.Entry<String, javax.sql.DataSource> dataSourceEntry : dataSources.entrySet()) {
			talendDataSources.put(dataSourceEntry.getKey(),
					new routines.system.TalendDataSource(dataSourceEntry.getValue()));
		}
		globalMap.put(KEY_DB_DATASOURCES, talendDataSources);
		globalMap.put(KEY_DB_DATASOURCES_RAW, new java.util.HashMap<String, javax.sql.DataSource>(dataSources));
	}

	JobStructureCatcherUtils talendJobLog = new JobStructureCatcherUtils(jobName, "_RnmB8JhhEeubEu0sfA4SPQ", "0.1");
	org.talend.job.audit.JobAuditLogger auditLogger_talendJobLog = null;

	private final java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
	private final java.io.PrintStream errorMessagePS = new java.io.PrintStream(new java.io.BufferedOutputStream(baos));

	public String getExceptionStackTrace() {
		if ("failure".equals(this.getStatus())) {
			errorMessagePS.flush();
			return baos.toString();
		}
		return null;
	}

	private Exception exception;

	public Exception getException() {
		if ("failure".equals(this.getStatus())) {
			return this.exception;
		}
		return null;
	}

	private class TalendException extends Exception {

		private static final long serialVersionUID = 1L;

		private java.util.Map<String, Object> globalMap = null;
		private Exception e = null;
		private String currentComponent = null;
		private String virtualComponentName = null;

		public void setVirtualComponentName(String virtualComponentName) {
			this.virtualComponentName = virtualComponentName;
		}

		private TalendException(Exception e, String errorComponent, final java.util.Map<String, Object> globalMap) {
			this.currentComponent = errorComponent;
			this.globalMap = globalMap;
			this.e = e;
		}

		public Exception getException() {
			return this.e;
		}

		public String getCurrentComponent() {
			return this.currentComponent;
		}

		public String getExceptionCauseMessage(Exception e) {
			Throwable cause = e;
			String message = null;
			int i = 10;
			while (null != cause && 0 < i--) {
				message = cause.getMessage();
				if (null == message) {
					cause = cause.getCause();
				} else {
					break;
				}
			}
			if (null == message) {
				message = e.getClass().getName();
			}
			return message;
		}

		@Override
		public void printStackTrace() {
			if (!(e instanceof TalendException || e instanceof TDieException)) {
				if (virtualComponentName != null && currentComponent.indexOf(virtualComponentName + "_") == 0) {
					globalMap.put(virtualComponentName + "_ERROR_MESSAGE", getExceptionCauseMessage(e));
				}
				globalMap.put(currentComponent + "_ERROR_MESSAGE", getExceptionCauseMessage(e));
				System.err.println("Exception in component " + currentComponent + " (" + jobName + ")");
			}
			if (!(e instanceof TDieException)) {
				if (e instanceof TalendException) {
					e.printStackTrace();
				} else {
					e.printStackTrace();
					e.printStackTrace(errorMessagePS);
					AddDatasetFromTDC_04.this.exception = e;
				}
			}
			if (!(e instanceof TalendException)) {
				try {
					for (java.lang.reflect.Method m : this.getClass().getEnclosingClass().getMethods()) {
						if (m.getName().compareTo(currentComponent + "_error") == 0) {
							m.invoke(AddDatasetFromTDC_04.this, new Object[] { e, currentComponent, globalMap });
							break;
						}
					}

					if (!(e instanceof TDieException)) {
					}
				} catch (Exception e) {
					this.e.printStackTrace();
				}
			}
		}
	}

	public void tRESTClient_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tRESTClient_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tExtractJSONFields_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tRESTClient_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFilterRow_6_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tRESTClient_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tLogRow_3_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tRESTClient_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tSetGlobalVar_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tRESTClient_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tRESTClient_3_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tRESTClient_3_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tExtractJSONFields_3_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tRESTClient_3_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tLogRow_2_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tRESTClient_3_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tSetGlobalVar_3_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tRESTClient_3_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tRESTClient_2_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tRESTClient_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tExtractJSONFields_2_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tRESTClient_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tLogRow_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tRESTClient_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tSetGlobalVar_2_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tRESTClient_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tMap_1_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tRESTClient_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tRESTClient_4_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tRESTClient_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void talendJobLog_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		talendJobLog_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tRESTClient_1_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void tRESTClient_3_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void tRESTClient_2_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void talendJobLog_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public static class projectIdRowStruct implements routines.system.IPersistableRow<projectIdRowStruct> {
		final static byte[] commonByteArrayLock_NSSOL_AddDatasetFromTDC_04 = new byte[0];
		static byte[] commonByteArray_NSSOL_AddDatasetFromTDC_04 = new byte[0];

		public String projectId;

		public String getProjectId() {
			return this.projectId;
		}

		public String projectName;

		public String getProjectName() {
			return this.projectName;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_NSSOL_AddDatasetFromTDC_04.length) {
					if (length < 1024 && commonByteArray_NSSOL_AddDatasetFromTDC_04.length == 0) {
						commonByteArray_NSSOL_AddDatasetFromTDC_04 = new byte[1024];
					} else {
						commonByteArray_NSSOL_AddDatasetFromTDC_04 = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_NSSOL_AddDatasetFromTDC_04, 0, length);
				strReturn = new String(commonByteArray_NSSOL_AddDatasetFromTDC_04, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_NSSOL_AddDatasetFromTDC_04) {

				try {

					int length = 0;

					this.projectId = readString(dis);

					this.projectName = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.projectId, dos);

				// String

				writeString(this.projectName, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("projectId=" + projectId);
			sb.append(",projectName=" + projectName);
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (projectId == null) {
				sb.append("<null>");
			} else {
				sb.append(projectId);
			}

			sb.append("|");

			if (projectName == null) {
				sb.append("<null>");
			} else {
				sb.append(projectName);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(projectIdRowStruct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row5Struct implements routines.system.IPersistableRow<row5Struct> {
		final static byte[] commonByteArrayLock_NSSOL_AddDatasetFromTDC_04 = new byte[0];
		static byte[] commonByteArray_NSSOL_AddDatasetFromTDC_04 = new byte[0];

		public String projectId;

		public String getProjectId() {
			return this.projectId;
		}

		public String projectName;

		public String getProjectName() {
			return this.projectName;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_NSSOL_AddDatasetFromTDC_04.length) {
					if (length < 1024 && commonByteArray_NSSOL_AddDatasetFromTDC_04.length == 0) {
						commonByteArray_NSSOL_AddDatasetFromTDC_04 = new byte[1024];
					} else {
						commonByteArray_NSSOL_AddDatasetFromTDC_04 = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_NSSOL_AddDatasetFromTDC_04, 0, length);
				strReturn = new String(commonByteArray_NSSOL_AddDatasetFromTDC_04, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_NSSOL_AddDatasetFromTDC_04) {

				try {

					int length = 0;

					this.projectId = readString(dis);

					this.projectName = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.projectId, dos);

				// String

				writeString(this.projectName, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("projectId=" + projectId);
			sb.append(",projectName=" + projectName);
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (projectId == null) {
				sb.append("<null>");
			} else {
				sb.append(projectId);
			}

			sb.append("|");

			if (projectName == null) {
				sb.append("<null>");
			} else {
				sb.append(projectName);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row5Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row2Struct implements routines.system.IPersistableRow<row2Struct> {
		final static byte[] commonByteArrayLock_NSSOL_AddDatasetFromTDC_04 = new byte[0];
		static byte[] commonByteArray_NSSOL_AddDatasetFromTDC_04 = new byte[0];

		public String projectId;

		public String getProjectId() {
			return this.projectId;
		}

		public String projectName;

		public String getProjectName() {
			return this.projectName;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_NSSOL_AddDatasetFromTDC_04.length) {
					if (length < 1024 && commonByteArray_NSSOL_AddDatasetFromTDC_04.length == 0) {
						commonByteArray_NSSOL_AddDatasetFromTDC_04 = new byte[1024];
					} else {
						commonByteArray_NSSOL_AddDatasetFromTDC_04 = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_NSSOL_AddDatasetFromTDC_04, 0, length);
				strReturn = new String(commonByteArray_NSSOL_AddDatasetFromTDC_04, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_NSSOL_AddDatasetFromTDC_04) {

				try {

					int length = 0;

					this.projectId = readString(dis);

					this.projectName = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.projectId, dos);

				// String

				writeString(this.projectName, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("projectId=" + projectId);
			sb.append(",projectName=" + projectName);
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (projectId == null) {
				sb.append("<null>");
			} else {
				sb.append(projectId);
			}

			sb.append("|");

			if (projectName == null) {
				sb.append("<null>");
			} else {
				sb.append(projectName);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row2Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row1Struct implements routines.system.IPersistableRow<row1Struct> {
		final static byte[] commonByteArrayLock_NSSOL_AddDatasetFromTDC_04 = new byte[0];
		static byte[] commonByteArray_NSSOL_AddDatasetFromTDC_04 = new byte[0];

		public Integer statusCode;

		public Integer getStatusCode() {
			return this.statusCode;
		}

		public routines.system.Document body;

		public routines.system.Document getBody() {
			return this.body;
		}

		public String string;

		public String getString() {
			return this.string;
		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_NSSOL_AddDatasetFromTDC_04.length) {
					if (length < 1024 && commonByteArray_NSSOL_AddDatasetFromTDC_04.length == 0) {
						commonByteArray_NSSOL_AddDatasetFromTDC_04 = new byte[1024];
					} else {
						commonByteArray_NSSOL_AddDatasetFromTDC_04 = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_NSSOL_AddDatasetFromTDC_04, 0, length);
				strReturn = new String(commonByteArray_NSSOL_AddDatasetFromTDC_04, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_NSSOL_AddDatasetFromTDC_04) {

				try {

					int length = 0;

					this.statusCode = readInteger(dis);

					this.body = (routines.system.Document) dis.readObject();

					this.string = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				} catch (ClassNotFoundException eCNFE) {
					throw new RuntimeException(eCNFE);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.statusCode, dos);

				// Document

				dos.writeObject(this.body);

				// String

				writeString(this.string, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("statusCode=" + String.valueOf(statusCode));
			sb.append(",body=" + String.valueOf(body));
			sb.append(",string=" + string);
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (statusCode == null) {
				sb.append("<null>");
			} else {
				sb.append(statusCode);
			}

			sb.append("|");

			if (body == null) {
				sb.append("<null>");
			} else {
				sb.append(body);
			}

			sb.append("|");

			if (string == null) {
				sb.append("<null>");
			} else {
				sb.append(string);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row1Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public void tRESTClient_1Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tRESTClient_1_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		String iterateId = "";

		String currentComponent = "";
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				row1Struct row1 = new row1Struct();
				row2Struct row2 = new row2Struct();
				row5Struct row5 = new row5Struct();
				row5Struct projectIdRow = row5;

				/**
				 * [tSetGlobalVar_1 begin ] start
				 */

				ok_Hash.put("tSetGlobalVar_1", false);
				start_Hash.put("tSetGlobalVar_1", System.currentTimeMillis());

				currentComponent = "tSetGlobalVar_1";

				runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "projectIdRow");

				int tos_count_tSetGlobalVar_1 = 0;

				if (log.isDebugEnabled())
					log.debug("tSetGlobalVar_1 - " + ("Start to work."));
				if (log.isDebugEnabled()) {
					class BytesLimit65535_tSetGlobalVar_1 {
						public void limitLog4jByte() throws Exception {
							StringBuilder log4jParamters_tSetGlobalVar_1 = new StringBuilder();
							log4jParamters_tSetGlobalVar_1.append("Parameters:");
							log4jParamters_tSetGlobalVar_1.append("VARIABLES" + " = " + "[{VALUE="
									+ ("projectIdRow.projectId") + ", KEY=" + ("\"varProjectId\"") + "}]");
							log4jParamters_tSetGlobalVar_1.append(" | ");
							if (log.isDebugEnabled())
								log.debug("tSetGlobalVar_1 - " + (log4jParamters_tSetGlobalVar_1));
						}
					}
					new BytesLimit65535_tSetGlobalVar_1().limitLog4jByte();
				}
				if (enableLogStash) {
					talendJobLog.addCM("tSetGlobalVar_1", "tSetGlobalVar");
					talendJobLogProcess(globalMap);
				}

				/**
				 * [tSetGlobalVar_1 begin ] stop
				 */

				/**
				 * [tLogRow_3 begin ] start
				 */

				ok_Hash.put("tLogRow_3", false);
				start_Hash.put("tLogRow_3", System.currentTimeMillis());

				currentComponent = "tLogRow_3";

				runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "row5");

				int tos_count_tLogRow_3 = 0;

				if (log.isDebugEnabled())
					log.debug("tLogRow_3 - " + ("Start to work."));
				if (log.isDebugEnabled()) {
					class BytesLimit65535_tLogRow_3 {
						public void limitLog4jByte() throws Exception {
							StringBuilder log4jParamters_tLogRow_3 = new StringBuilder();
							log4jParamters_tLogRow_3.append("Parameters:");
							log4jParamters_tLogRow_3.append("BASIC_MODE" + " = " + "true");
							log4jParamters_tLogRow_3.append(" | ");
							log4jParamters_tLogRow_3.append("TABLE_PRINT" + " = " + "false");
							log4jParamters_tLogRow_3.append(" | ");
							log4jParamters_tLogRow_3.append("VERTICAL" + " = " + "false");
							log4jParamters_tLogRow_3.append(" | ");
							log4jParamters_tLogRow_3.append("FIELDSEPARATOR" + " = " + "\",\"");
							log4jParamters_tLogRow_3.append(" | ");
							log4jParamters_tLogRow_3.append("PRINT_HEADER" + " = " + "false");
							log4jParamters_tLogRow_3.append(" | ");
							log4jParamters_tLogRow_3.append("PRINT_UNIQUE_NAME" + " = " + "false");
							log4jParamters_tLogRow_3.append(" | ");
							log4jParamters_tLogRow_3.append("PRINT_COLNAMES" + " = " + "false");
							log4jParamters_tLogRow_3.append(" | ");
							log4jParamters_tLogRow_3.append("USE_FIXED_LENGTH" + " = " + "false");
							log4jParamters_tLogRow_3.append(" | ");
							log4jParamters_tLogRow_3.append("PRINT_CONTENT_WITH_LOG4J" + " = " + "true");
							log4jParamters_tLogRow_3.append(" | ");
							if (log.isDebugEnabled())
								log.debug("tLogRow_3 - " + (log4jParamters_tLogRow_3));
						}
					}
					new BytesLimit65535_tLogRow_3().limitLog4jByte();
				}
				if (enableLogStash) {
					talendJobLog.addCM("tLogRow_3", "tLogRow");
					talendJobLogProcess(globalMap);
				}

				///////////////////////

				final String OUTPUT_FIELD_SEPARATOR_tLogRow_3 = ",";
				java.io.PrintStream consoleOut_tLogRow_3 = null;

				StringBuilder strBuffer_tLogRow_3 = null;
				int nb_line_tLogRow_3 = 0;
///////////////////////    			

				/**
				 * [tLogRow_3 begin ] stop
				 */

				/**
				 * [tFilterRow_6 begin ] start
				 */

				ok_Hash.put("tFilterRow_6", false);
				start_Hash.put("tFilterRow_6", System.currentTimeMillis());

				currentComponent = "tFilterRow_6";

				runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "row2");

				int tos_count_tFilterRow_6 = 0;

				if (log.isDebugEnabled())
					log.debug("tFilterRow_6 - " + ("Start to work."));
				if (log.isDebugEnabled()) {
					class BytesLimit65535_tFilterRow_6 {
						public void limitLog4jByte() throws Exception {
							StringBuilder log4jParamters_tFilterRow_6 = new StringBuilder();
							log4jParamters_tFilterRow_6.append("Parameters:");
							log4jParamters_tFilterRow_6.append("LOGICAL_OP" + " = " + "&&");
							log4jParamters_tFilterRow_6.append(" | ");
							log4jParamters_tFilterRow_6.append("CONDITIONS" + " = " + "[{OPERATOR=" + ("==")
									+ ", RVALUE=" + ("context.projectName") + ", INPUT_COLUMN=" + ("projectName")
									+ ", FUNCTION=" + ("") + "}]");
							log4jParamters_tFilterRow_6.append(" | ");
							log4jParamters_tFilterRow_6.append("USE_ADVANCED" + " = " + "false");
							log4jParamters_tFilterRow_6.append(" | ");
							if (log.isDebugEnabled())
								log.debug("tFilterRow_6 - " + (log4jParamters_tFilterRow_6));
						}
					}
					new BytesLimit65535_tFilterRow_6().limitLog4jByte();
				}
				if (enableLogStash) {
					talendJobLog.addCM("tFilterRow_6", "tFilterRow");
					talendJobLogProcess(globalMap);
				}

				int nb_line_tFilterRow_6 = 0;
				int nb_line_ok_tFilterRow_6 = 0;
				int nb_line_reject_tFilterRow_6 = 0;

				class Operator_tFilterRow_6 {
					private String sErrorMsg = "";
					private boolean bMatchFlag = true;
					private String sUnionFlag = "&&";

					public Operator_tFilterRow_6(String unionFlag) {
						sUnionFlag = unionFlag;
						bMatchFlag = "||".equals(unionFlag) ? false : true;
					}

					public String getErrorMsg() {
						if (sErrorMsg != null && sErrorMsg.length() > 1)
							return sErrorMsg.substring(1);
						else
							return null;
					}

					public boolean getMatchFlag() {
						return bMatchFlag;
					}

					public void matches(boolean partMatched, String reason) {
						// no need to care about the next judgement
						if ("||".equals(sUnionFlag) && bMatchFlag) {
							return;
						}

						if (!partMatched) {
							sErrorMsg += "|" + reason;
						}

						if ("||".equals(sUnionFlag))
							bMatchFlag = bMatchFlag || partMatched;
						else
							bMatchFlag = bMatchFlag && partMatched;
					}
				}

				/**
				 * [tFilterRow_6 begin ] stop
				 */

				/**
				 * [tExtractJSONFields_1 begin ] start
				 */

				ok_Hash.put("tExtractJSONFields_1", false);
				start_Hash.put("tExtractJSONFields_1", System.currentTimeMillis());

				currentComponent = "tExtractJSONFields_1";

				runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "row1");

				int tos_count_tExtractJSONFields_1 = 0;

				if (log.isDebugEnabled())
					log.debug("tExtractJSONFields_1 - " + ("Start to work."));
				if (log.isDebugEnabled()) {
					class BytesLimit65535_tExtractJSONFields_1 {
						public void limitLog4jByte() throws Exception {
							StringBuilder log4jParamters_tExtractJSONFields_1 = new StringBuilder();
							log4jParamters_tExtractJSONFields_1.append("Parameters:");
							log4jParamters_tExtractJSONFields_1.append("READ_BY" + " = " + "JSONPATH");
							log4jParamters_tExtractJSONFields_1.append(" | ");
							log4jParamters_tExtractJSONFields_1.append("JSON_PATH_VERSION" + " = " + "2_1_0");
							log4jParamters_tExtractJSONFields_1.append(" | ");
							log4jParamters_tExtractJSONFields_1.append("JSONFIELD" + " = " + "string");
							log4jParamters_tExtractJSONFields_1.append(" | ");
							log4jParamters_tExtractJSONFields_1.append("JSON_LOOP_QUERY" + " = " + "\"$.items.[*]\"");
							log4jParamters_tExtractJSONFields_1.append(" | ");
							log4jParamters_tExtractJSONFields_1.append("MAPPING_4_JSONPATH" + " = " + "[{QUERY="
									+ ("\"id\"") + ", SCHEMA_COLUMN=" + ("projectId") + "}, {QUERY=" + ("\"name\"")
									+ ", SCHEMA_COLUMN=" + ("projectName") + "}]");
							log4jParamters_tExtractJSONFields_1.append(" | ");
							log4jParamters_tExtractJSONFields_1.append("DIE_ON_ERROR" + " = " + "false");
							log4jParamters_tExtractJSONFields_1.append(" | ");
							log4jParamters_tExtractJSONFields_1.append("USE_LOOP_AS_ROOT" + " = " + "true");
							log4jParamters_tExtractJSONFields_1.append(" | ");
							if (log.isDebugEnabled())
								log.debug("tExtractJSONFields_1 - " + (log4jParamters_tExtractJSONFields_1));
						}
					}
					new BytesLimit65535_tExtractJSONFields_1().limitLog4jByte();
				}
				if (enableLogStash) {
					talendJobLog.addCM("tExtractJSONFields_1", "tExtractJSONFields");
					talendJobLogProcess(globalMap);
				}

				int nb_line_tExtractJSONFields_1 = 0;
				String jsonStr_tExtractJSONFields_1 = "";

				class JsonPathCache_tExtractJSONFields_1 {
					final java.util.Map<String, com.jayway.jsonpath.JsonPath> jsonPathString2compiledJsonPath = new java.util.HashMap<String, com.jayway.jsonpath.JsonPath>();

					public com.jayway.jsonpath.JsonPath getCompiledJsonPath(String jsonPath) {
						if (jsonPathString2compiledJsonPath.containsKey(jsonPath)) {
							return jsonPathString2compiledJsonPath.get(jsonPath);
						} else {
							com.jayway.jsonpath.JsonPath compiledLoopPath = com.jayway.jsonpath.JsonPath
									.compile(jsonPath);
							jsonPathString2compiledJsonPath.put(jsonPath, compiledLoopPath);
							return compiledLoopPath;
						}
					}
				}

				JsonPathCache_tExtractJSONFields_1 jsonPathCache_tExtractJSONFields_1 = new JsonPathCache_tExtractJSONFields_1();

				/**
				 * [tExtractJSONFields_1 begin ] stop
				 */

				/**
				 * [tRESTClient_1 begin ] start
				 */

				ok_Hash.put("tRESTClient_1", false);
				start_Hash.put("tRESTClient_1", System.currentTimeMillis());

				currentComponent = "tRESTClient_1";

				int tos_count_tRESTClient_1 = 0;

				if (enableLogStash) {
					talendJobLog.addCM("tRESTClient_1", "tRESTClient");
					talendJobLogProcess(globalMap);
				}

				/**
				 * [tRESTClient_1 begin ] stop
				 */

				/**
				 * [tRESTClient_1 main ] start
				 */

				currentComponent = "tRESTClient_1";

				row1 = null;

// expected response body
				Object responseDoc_tRESTClient_1 = null;

				try {
					// request body
					org.dom4j.Document requestDoc_tRESTClient_1 = null;
					String requestString_tRESTClient_1 = null;

					Object requestBody_tRESTClient_1 = requestDoc_tRESTClient_1 != null ? requestDoc_tRESTClient_1
							: requestString_tRESTClient_1;

					// resposne class name
					Class<?> responseClass_tRESTClient_1 = String.class;

					// create web client instance
					org.apache.cxf.jaxrs.client.JAXRSClientFactoryBean factoryBean_tRESTClient_1 = new org.apache.cxf.jaxrs.client.JAXRSClientFactoryBean();

					boolean inOSGi = routines.system.BundleUtils.inOSGi();

					final java.util.List<org.apache.cxf.feature.Feature> features_tRESTClient_1 = new java.util.ArrayList<org.apache.cxf.feature.Feature>();

					String url = "https://api.ap.cloud.talend.com/tmc/v2.3/projects";
					// {baseUri}tRESTClient
					factoryBean_tRESTClient_1.setServiceName(new javax.xml.namespace.QName(url, "tRESTClient"));
					factoryBean_tRESTClient_1.setAddress(url);

					factoryBean_tRESTClient_1.setFeatures(features_tRESTClient_1);

					java.util.List<Object> providers_tRESTClient_1 = new java.util.ArrayList<Object>();
					providers_tRESTClient_1.add(new org.apache.cxf.jaxrs.provider.dom4j.DOM4JProvider() {
						// workaround for https://jira.talendforge.org/browse/TESB-7276
						public org.dom4j.Document readFrom(Class<org.dom4j.Document> cls, java.lang.reflect.Type type,
								java.lang.annotation.Annotation[] anns, javax.ws.rs.core.MediaType mt,
								javax.ws.rs.core.MultivaluedMap<String, String> headers, java.io.InputStream is)
								throws IOException, javax.ws.rs.WebApplicationException {
							String contentLength = headers.getFirst("Content-Length");
							if (!org.apache.cxf.common.util.StringUtils.isEmpty(contentLength)
									&& Integer.valueOf(contentLength) <= 0) {
								try {
									return org.dom4j.DocumentHelper.parseText("<root/>");
								} catch (org.dom4j.DocumentException e_tRESTClient_1) {
									e_tRESTClient_1.printStackTrace();
								}
								return null;
							}
							return super.readFrom(cls, type, anns, mt, headers, is);
						}
					});
					org.apache.cxf.jaxrs.provider.json.JSONProvider jsonProvider_tRESTClient_1 = new org.apache.cxf.jaxrs.provider.json.JSONProvider();
					jsonProvider_tRESTClient_1.setIgnoreNamespaces(true);
					jsonProvider_tRESTClient_1.setAttributesToElements(true);

					jsonProvider_tRESTClient_1.setSupportUnwrapped(true);
					jsonProvider_tRESTClient_1.setWrapperName("root");

					jsonProvider_tRESTClient_1.setDropRootElement(false);
					jsonProvider_tRESTClient_1.setConvertTypesToStrings(false);
					providers_tRESTClient_1.add(jsonProvider_tRESTClient_1);
					factoryBean_tRESTClient_1.setProviders(providers_tRESTClient_1);
					factoryBean_tRESTClient_1.setTransportId("http://cxf.apache.org/transports/http");

					boolean use_auth_tRESTClient_1 = true;

					if (use_auth_tRESTClient_1 && "SAML".equals("OAUTH2_BEARER")) {
						if (!inOSGi) {
							throw new IllegalArgumentException(
									"SAML based security scenarios are not supported in Studio (standalone)");
						}
						// set SAML Token authentication

						final String decryptedPassword_tRESTClient_1 = routines.system.PasswordEncryptUtil
								.decryptPassword(
										"enc:routine.encryption.key.v1:78lbogoBHarVCbOc0EHBs2zxoYf8iNpERzobqPdOutbKxJQ3nA==");

						org.apache.cxf.ws.security.trust.STSClient stsClient = org.talend.esb.security.saml.STSClientUtils
								.createSTSClient(factoryBean_tRESTClient_1.getBus(), "tanakatanaka2@digdig.org",
										decryptedPassword_tRESTClient_1);

						org.talend.esb.security.saml.SAMLRESTUtils.configureClient(factoryBean_tRESTClient_1,
								stsClient);
					}

					org.apache.cxf.jaxrs.client.WebClient webClient_tRESTClient_1 = factoryBean_tRESTClient_1
							.createWebClient();

					// set request path
					webClient_tRESTClient_1.path("");

					// set connection properties
					org.apache.cxf.jaxrs.client.ClientConfiguration clientConfig_tRESTClient_1 = org.apache.cxf.jaxrs.client.WebClient
							.getConfig(webClient_tRESTClient_1);
					org.apache.cxf.transport.http.HTTPConduit conduit_tRESTClient_1 = clientConfig_tRESTClient_1
							.getHttpConduit();

					if (clientConfig_tRESTClient_1.getEndpoint() != null
							&& clientConfig_tRESTClient_1.getEndpoint().getEndpointInfo() != null) {
						clientConfig_tRESTClient_1.getEndpoint().getEndpointInfo()
								.setProperty("enable.webclient.operation.reporting", true);
					}

					if (use_auth_tRESTClient_1 && "BASIC".equals("OAUTH2_BEARER")) {
						// set BASIC auth

						final String decryptedPassword_tRESTClient_1 = routines.system.PasswordEncryptUtil
								.decryptPassword(
										"enc:routine.encryption.key.v1:UQ0NI1xzDfIqqG5jQdY5eB0zjIRROoI2t5eHzHfngoM32asbQg==");

						org.apache.cxf.configuration.security.AuthorizationPolicy authPolicy_tRESTClient_1 = new org.apache.cxf.configuration.security.AuthorizationPolicy();
						authPolicy_tRESTClient_1.setAuthorizationType("Basic");
						authPolicy_tRESTClient_1.setUserName("tanakatanaka2@digdig.org");
						authPolicy_tRESTClient_1.setPassword(decryptedPassword_tRESTClient_1);
						conduit_tRESTClient_1.setAuthorization(authPolicy_tRESTClient_1);
					} else if (use_auth_tRESTClient_1 && "HTTP Digest".equals("OAUTH2_BEARER")) {
						// set Digest auth

						final String decryptedPassword_tRESTClient_1 = routines.system.PasswordEncryptUtil
								.decryptPassword(
										"enc:routine.encryption.key.v1:xEGHC+iw/JdfhYT4ZDvpbcgImDkIXg05c6+aa5pgxMhuAE7zQA==");

						org.apache.cxf.configuration.security.AuthorizationPolicy authPolicy_tRESTClient_1 = new org.apache.cxf.configuration.security.AuthorizationPolicy();
						authPolicy_tRESTClient_1.setAuthorizationType("Digest");
						authPolicy_tRESTClient_1.setUserName("tanakatanaka2@digdig.org");
						authPolicy_tRESTClient_1.setPassword(decryptedPassword_tRESTClient_1);
						conduit_tRESTClient_1.setAuthorization(authPolicy_tRESTClient_1);
					}

					if (!inOSGi) {
						conduit_tRESTClient_1.getClient().setReceiveTimeout((long) (60 * 1000L));
						conduit_tRESTClient_1.getClient().setConnectionTimeout((long) (30 * 1000L));
						boolean use_proxy_tRESTClient_1 = false;

					}

					// set Accept-Type
					webClient_tRESTClient_1.accept("application/json");

					// set optional query and header properties if any

					if (use_auth_tRESTClient_1 && "OAUTH2_BEARER".equals("OAUTH2_BEARER")) {
						// set oAuth2 bearer token
						webClient_tRESTClient_1.header("Authorization",
								"Bearer " + "N1Hi0zFKRxie79flQEBmNubF8okwmsQgP1Eyhb93Slz8MKgtRVKQQXXdN0_PZQRP");
					}

					if (use_auth_tRESTClient_1 && "OIDC_PASSWORD_GRANT".equals("OAUTH2_BEARER")) {

						final String decryptedPassword_tRESTClient_1 = routines.system.PasswordEncryptUtil
								.decryptPassword(
										"enc:routine.encryption.key.v1:0uoYZcjPHGDf64UbYik4NTahFmuh0yZyc/pCqfmUIpE2froDhw==");

						String username_tRESTClient_1 = "tanakatanaka2@digdig.org";
						String password_tRESTClient_1 = decryptedPassword_tRESTClient_1;

						String bearerHeader_tRESTClient_1 = null;

						try {
							if (!inOSGi) {
								org.springframework.context.support.ClassPathXmlApplicationContext context_tRESTClient_1 = new org.springframework.context.support.ClassPathXmlApplicationContext(
										"META-INF/tesb/oidc-context.xml");
								org.talend.esb.security.oidc.OidcConfiguration oidcConfiguration_tRESTClient_1 = context_tRESTClient_1
										.getBean(org.talend.esb.security.oidc.OidcConfiguration.class);
								bearerHeader_tRESTClient_1 = org.talend.esb.security.oidc.OidcClientUtils
										.oidcClientBearer(username_tRESTClient_1, password_tRESTClient_1,
												oidcConfiguration_tRESTClient_1);
							} else {
								bearerHeader_tRESTClient_1 = org.talend.esb.security.oidc.OidcClientUtils
										.oidcClientBearer(username_tRESTClient_1, password_tRESTClient_1);
							}
						} catch (Exception ex) {
							throw new javax.ws.rs.WebApplicationException("Failed to get OIDC access token: ", ex);
						}

						webClient_tRESTClient_1.header("Authorization", bearerHeader_tRESTClient_1);
					}

					// if FORM request then capture query parameters into Form, otherwise set them
					// as queries

					try {
						// start send request

						responseDoc_tRESTClient_1 = webClient_tRESTClient_1.get(responseClass_tRESTClient_1);

						int webClientResponseStatus_tRESTClient_1 = webClient_tRESTClient_1.getResponse().getStatus();
						if (webClientResponseStatus_tRESTClient_1 >= 300) {
							throw new javax.ws.rs.WebApplicationException(webClient_tRESTClient_1.getResponse());
						}

						if (row1 == null) {
							row1 = new row1Struct();
						}

						row1.statusCode = webClientResponseStatus_tRESTClient_1;

						{
							Object responseObj_tRESTClient_1 = responseDoc_tRESTClient_1;

							if (responseObj_tRESTClient_1 != null) {
								if (responseClass_tRESTClient_1 == String.class) {
									row1.string = (String) responseObj_tRESTClient_1;
								} else {
									routines.system.Document responseTalendDoc_tRESTClient_1 = null;
									if (null != responseObj_tRESTClient_1) {
										responseTalendDoc_tRESTClient_1 = new routines.system.Document();
										responseTalendDoc_tRESTClient_1
												.setDocument((org.dom4j.Document) responseObj_tRESTClient_1);
									}
									row1.body = responseTalendDoc_tRESTClient_1;
								}
							}
						}

						globalMap.put("tRESTClient_1_HEADERS", webClient_tRESTClient_1.getResponse().getHeaders());

					} catch (javax.ws.rs.WebApplicationException ex_tRESTClient_1) {

						throw ex_tRESTClient_1;

					}

				} catch (Exception e_tRESTClient_1) {

					throw new TalendException(e_tRESTClient_1, currentComponent, globalMap);

				}

				tos_count_tRESTClient_1++;

				/**
				 * [tRESTClient_1 main ] stop
				 */

				/**
				 * [tRESTClient_1 process_data_begin ] start
				 */

				currentComponent = "tRESTClient_1";

				/**
				 * [tRESTClient_1 process_data_begin ] stop
				 */
// Start of branch "row1"
				if (row1 != null) {

					/**
					 * [tExtractJSONFields_1 main ] start
					 */

					currentComponent = "tExtractJSONFields_1";

					runStat.updateStatAndLog(execStat, enableLogStash, iterateId, 1, 1, "row1");

					if (log.isTraceEnabled()) {
						log.trace("row1 - " + (row1 == null ? "" : row1.toLogString()));
					}

					if (row1.string != null) {// C_01
						jsonStr_tExtractJSONFields_1 = row1.string.toString();

						row2 = null;

						String loopPath_tExtractJSONFields_1 = "$.items.[*]";
						java.util.List<Object> resultset_tExtractJSONFields_1 = new java.util.ArrayList<Object>();

						boolean isStructError_tExtractJSONFields_1 = true;
						com.jayway.jsonpath.ReadContext document_tExtractJSONFields_1 = null;
						try {
							document_tExtractJSONFields_1 = com.jayway.jsonpath.JsonPath
									.parse(jsonStr_tExtractJSONFields_1);
							com.jayway.jsonpath.JsonPath compiledLoopPath_tExtractJSONFields_1 = jsonPathCache_tExtractJSONFields_1
									.getCompiledJsonPath(loopPath_tExtractJSONFields_1);
							Object result_tExtractJSONFields_1 = document_tExtractJSONFields_1
									.read(compiledLoopPath_tExtractJSONFields_1, net.minidev.json.JSONObject.class);
							if (result_tExtractJSONFields_1 instanceof net.minidev.json.JSONArray) {
								resultset_tExtractJSONFields_1 = (net.minidev.json.JSONArray) result_tExtractJSONFields_1;
							} else {
								resultset_tExtractJSONFields_1.add(result_tExtractJSONFields_1);
							}

							isStructError_tExtractJSONFields_1 = false;
						} catch (java.lang.Exception ex_tExtractJSONFields_1) {
							log.error("tExtractJSONFields_1 - " + ex_tExtractJSONFields_1.getMessage());
							System.err.println(ex_tExtractJSONFields_1.getMessage());
						}

						String jsonPath_tExtractJSONFields_1 = null;
						com.jayway.jsonpath.JsonPath compiledJsonPath_tExtractJSONFields_1 = null;

						Object value_tExtractJSONFields_1 = null;

						Object root_tExtractJSONFields_1 = null;
						for (int i_tExtractJSONFields_1 = 0; isStructError_tExtractJSONFields_1
								|| (i_tExtractJSONFields_1 < resultset_tExtractJSONFields_1
										.size()); i_tExtractJSONFields_1++) {
							if (!isStructError_tExtractJSONFields_1) {
								Object row_tExtractJSONFields_1 = resultset_tExtractJSONFields_1
										.get(i_tExtractJSONFields_1);
								row2 = null;
								row2 = new row2Struct();
								nb_line_tExtractJSONFields_1++;
								try {
									jsonPath_tExtractJSONFields_1 = "id";
									compiledJsonPath_tExtractJSONFields_1 = jsonPathCache_tExtractJSONFields_1
											.getCompiledJsonPath(jsonPath_tExtractJSONFields_1);

									try {

										value_tExtractJSONFields_1 = compiledJsonPath_tExtractJSONFields_1
												.read(row_tExtractJSONFields_1);

										row2.projectId = value_tExtractJSONFields_1 == null ?

												null

												: value_tExtractJSONFields_1.toString();
									} catch (com.jayway.jsonpath.PathNotFoundException e_tExtractJSONFields_1) {
										row2.projectId =

												null

										;
									}
									jsonPath_tExtractJSONFields_1 = "name";
									compiledJsonPath_tExtractJSONFields_1 = jsonPathCache_tExtractJSONFields_1
											.getCompiledJsonPath(jsonPath_tExtractJSONFields_1);

									try {

										value_tExtractJSONFields_1 = compiledJsonPath_tExtractJSONFields_1
												.read(row_tExtractJSONFields_1);

										row2.projectName = value_tExtractJSONFields_1 == null ?

												null

												: value_tExtractJSONFields_1.toString();
									} catch (com.jayway.jsonpath.PathNotFoundException e_tExtractJSONFields_1) {
										row2.projectName =

												null

										;
									}
								} catch (java.lang.Exception ex_tExtractJSONFields_1) {
									log.error("tExtractJSONFields_1 - " + ex_tExtractJSONFields_1.getMessage());
									System.err.println(ex_tExtractJSONFields_1.getMessage());
									row2 = null;
								}

							}

							isStructError_tExtractJSONFields_1 = false;

							log.debug("tExtractJSONFields_1 - Extracting the record " + nb_line_tExtractJSONFields_1
									+ ".");
//}

							tos_count_tExtractJSONFields_1++;

							/**
							 * [tExtractJSONFields_1 main ] stop
							 */

							/**
							 * [tExtractJSONFields_1 process_data_begin ] start
							 */

							currentComponent = "tExtractJSONFields_1";

							/**
							 * [tExtractJSONFields_1 process_data_begin ] stop
							 */
// Start of branch "row2"
							if (row2 != null) {

								/**
								 * [tFilterRow_6 main ] start
								 */

								currentComponent = "tFilterRow_6";

								runStat.updateStatAndLog(execStat, enableLogStash, iterateId, 1, 1, "row2");

								if (log.isTraceEnabled()) {
									log.trace("row2 - " + (row2 == null ? "" : row2.toLogString()));
								}

								row5 = null;
								Operator_tFilterRow_6 ope_tFilterRow_6 = new Operator_tFilterRow_6("&&");
								ope_tFilterRow_6.matches(
										(row2.projectName == null ? false
												: row2.projectName.compareTo(context.projectName) == 0),
										"projectName.compareTo(context.projectName) == 0 failed");

								if (ope_tFilterRow_6.getMatchFlag()) {
									if (row5 == null) {
										row5 = new row5Struct();
									}
									row5.projectId = row2.projectId;
									row5.projectName = row2.projectName;
									log.debug("tFilterRow_6 - Process the record " + (nb_line_tFilterRow_6 + 1) + ".");

									nb_line_ok_tFilterRow_6++;
								} else {
									nb_line_reject_tFilterRow_6++;
								}

								nb_line_tFilterRow_6++;

								tos_count_tFilterRow_6++;

								/**
								 * [tFilterRow_6 main ] stop
								 */

								/**
								 * [tFilterRow_6 process_data_begin ] start
								 */

								currentComponent = "tFilterRow_6";

								/**
								 * [tFilterRow_6 process_data_begin ] stop
								 */
// Start of branch "row5"
								if (row5 != null) {

									/**
									 * [tLogRow_3 main ] start
									 */

									currentComponent = "tLogRow_3";

									runStat.updateStatAndLog(execStat, enableLogStash, iterateId, 1, 1, "row5");

									if (log.isTraceEnabled()) {
										log.trace("row5 - " + (row5 == null ? "" : row5.toLogString()));
									}

///////////////////////		

									strBuffer_tLogRow_3 = new StringBuilder();

									if (row5.projectId != null) { //

										strBuffer_tLogRow_3.append(String.valueOf(row5.projectId));

									} //

									strBuffer_tLogRow_3.append(",");

									if (row5.projectName != null) { //

										strBuffer_tLogRow_3.append(String.valueOf(row5.projectName));

									} //

									if (globalMap.get("tLogRow_CONSOLE") != null) {
										consoleOut_tLogRow_3 = (java.io.PrintStream) globalMap.get("tLogRow_CONSOLE");
									} else {
										consoleOut_tLogRow_3 = new java.io.PrintStream(
												new java.io.BufferedOutputStream(System.out));
										globalMap.put("tLogRow_CONSOLE", consoleOut_tLogRow_3);
									}
									log.info("tLogRow_3 - Content of row " + (nb_line_tLogRow_3 + 1) + ": "
											+ strBuffer_tLogRow_3.toString());
									consoleOut_tLogRow_3.println(strBuffer_tLogRow_3.toString());
									consoleOut_tLogRow_3.flush();
									nb_line_tLogRow_3++;
//////

//////                    

///////////////////////    			

									projectIdRow = row5;

									tos_count_tLogRow_3++;

									/**
									 * [tLogRow_3 main ] stop
									 */

									/**
									 * [tLogRow_3 process_data_begin ] start
									 */

									currentComponent = "tLogRow_3";

									/**
									 * [tLogRow_3 process_data_begin ] stop
									 */

									/**
									 * [tSetGlobalVar_1 main ] start
									 */

									currentComponent = "tSetGlobalVar_1";

									runStat.updateStatAndLog(execStat, enableLogStash, iterateId, 1, 1, "projectIdRow");

									if (log.isTraceEnabled()) {
										log.trace("projectIdRow - "
												+ (projectIdRow == null ? "" : projectIdRow.toLogString()));
									}

									globalMap.put("varProjectId", projectIdRow.projectId);

									tos_count_tSetGlobalVar_1++;

									/**
									 * [tSetGlobalVar_1 main ] stop
									 */

									/**
									 * [tSetGlobalVar_1 process_data_begin ] start
									 */

									currentComponent = "tSetGlobalVar_1";

									/**
									 * [tSetGlobalVar_1 process_data_begin ] stop
									 */

									/**
									 * [tSetGlobalVar_1 process_data_end ] start
									 */

									currentComponent = "tSetGlobalVar_1";

									/**
									 * [tSetGlobalVar_1 process_data_end ] stop
									 */

									/**
									 * [tLogRow_3 process_data_end ] start
									 */

									currentComponent = "tLogRow_3";

									/**
									 * [tLogRow_3 process_data_end ] stop
									 */

								} // End of branch "row5"

								/**
								 * [tFilterRow_6 process_data_end ] start
								 */

								currentComponent = "tFilterRow_6";

								/**
								 * [tFilterRow_6 process_data_end ] stop
								 */

							} // End of branch "row2"

							// end for
						}

					} // C_01

					/**
					 * [tExtractJSONFields_1 process_data_end ] start
					 */

					currentComponent = "tExtractJSONFields_1";

					/**
					 * [tExtractJSONFields_1 process_data_end ] stop
					 */

				} // End of branch "row1"

				/**
				 * [tRESTClient_1 process_data_end ] start
				 */

				currentComponent = "tRESTClient_1";

				/**
				 * [tRESTClient_1 process_data_end ] stop
				 */

				/**
				 * [tRESTClient_1 end ] start
				 */

				currentComponent = "tRESTClient_1";

				if (globalMap.get("tRESTClient_1_NB_LINE") == null) {
					globalMap.put("tRESTClient_1_NB_LINE", 1);
				}

// [tRESTCliend_end]

				ok_Hash.put("tRESTClient_1", true);
				end_Hash.put("tRESTClient_1", System.currentTimeMillis());

				/**
				 * [tRESTClient_1 end ] stop
				 */

				/**
				 * [tExtractJSONFields_1 end ] start
				 */

				currentComponent = "tExtractJSONFields_1";

				globalMap.put("tExtractJSONFields_1_NB_LINE", nb_line_tExtractJSONFields_1);
				log.debug("tExtractJSONFields_1 - Extracted records count: " + nb_line_tExtractJSONFields_1 + " .");

				if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "row1", 2, 0,
						talendJobLog, "tRESTClient_1", "tRESTClient", "tExtractJSONFields_1", "tExtractJSONFields",
						"output")) {
					talendJobLogProcess(globalMap);
				}

				if (log.isDebugEnabled())
					log.debug("tExtractJSONFields_1 - " + ("Done."));

				ok_Hash.put("tExtractJSONFields_1", true);
				end_Hash.put("tExtractJSONFields_1", System.currentTimeMillis());

				/**
				 * [tExtractJSONFields_1 end ] stop
				 */

				/**
				 * [tFilterRow_6 end ] start
				 */

				currentComponent = "tFilterRow_6";

				globalMap.put("tFilterRow_6_NB_LINE", nb_line_tFilterRow_6);
				globalMap.put("tFilterRow_6_NB_LINE_OK", nb_line_ok_tFilterRow_6);
				globalMap.put("tFilterRow_6_NB_LINE_REJECT", nb_line_reject_tFilterRow_6);

				log.info("tFilterRow_6 - Processed records count:" + nb_line_tFilterRow_6 + ". Matched records count:"
						+ nb_line_ok_tFilterRow_6 + ". Rejected records count:" + nb_line_reject_tFilterRow_6 + ".");

				if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "row2", 2, 0,
						talendJobLog, "tExtractJSONFields_1", "tExtractJSONFields", "tFilterRow_6", "tFilterRow",
						"output")) {
					talendJobLogProcess(globalMap);
				}

				if (log.isDebugEnabled())
					log.debug("tFilterRow_6 - " + ("Done."));

				ok_Hash.put("tFilterRow_6", true);
				end_Hash.put("tFilterRow_6", System.currentTimeMillis());

				/**
				 * [tFilterRow_6 end ] stop
				 */

				/**
				 * [tLogRow_3 end ] start
				 */

				currentComponent = "tLogRow_3";

//////
//////
				globalMap.put("tLogRow_3_NB_LINE", nb_line_tLogRow_3);
				if (log.isInfoEnabled())
					log.info("tLogRow_3 - " + ("Printed row count: ") + (nb_line_tLogRow_3) + ("."));

///////////////////////    			

				if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "row5", 2, 0,
						talendJobLog, "tFilterRow_6", "tFilterRow", "tLogRow_3", "tLogRow", "output")) {
					talendJobLogProcess(globalMap);
				}

				if (log.isDebugEnabled())
					log.debug("tLogRow_3 - " + ("Done."));

				ok_Hash.put("tLogRow_3", true);
				end_Hash.put("tLogRow_3", System.currentTimeMillis());

				/**
				 * [tLogRow_3 end ] stop
				 */

				/**
				 * [tSetGlobalVar_1 end ] start
				 */

				currentComponent = "tSetGlobalVar_1";

				if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "projectIdRow", 2, 0,
						talendJobLog, "tLogRow_3", "tLogRow", "tSetGlobalVar_1", "tSetGlobalVar", "output")) {
					talendJobLogProcess(globalMap);
				}

				if (log.isDebugEnabled())
					log.debug("tSetGlobalVar_1 - " + ("Done."));

				ok_Hash.put("tSetGlobalVar_1", true);
				end_Hash.put("tSetGlobalVar_1", System.currentTimeMillis());

				/**
				 * [tSetGlobalVar_1 end ] stop
				 */

			} // end the resume

			if (resumeEntryMethodName == null || globalResumeTicket) {
				resumeUtil.addLog("CHECKPOINT", "CONNECTION:SUBJOB_OK:tRESTClient_1:OnSubjobOk", "",
						Thread.currentThread().getId() + "", "", "", "", "", "");
			}

			if (execStat) {
				runStat.updateStatOnConnection("OnSubjobOk1", 0, "ok");
			}

			tRESTClient_3Process(globalMap);

		} catch (java.lang.Exception e) {

			if (!(e instanceof TalendException)) {
				log.fatal(currentComponent + " " + e.getMessage(), e);
			}

			TalendException te = new TalendException(e, currentComponent, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			try {

				/**
				 * [tRESTClient_1 finally ] start
				 */

				currentComponent = "tRESTClient_1";

				/**
				 * [tRESTClient_1 finally ] stop
				 */

				/**
				 * [tExtractJSONFields_1 finally ] start
				 */

				currentComponent = "tExtractJSONFields_1";

				/**
				 * [tExtractJSONFields_1 finally ] stop
				 */

				/**
				 * [tFilterRow_6 finally ] start
				 */

				currentComponent = "tFilterRow_6";

				/**
				 * [tFilterRow_6 finally ] stop
				 */

				/**
				 * [tLogRow_3 finally ] start
				 */

				currentComponent = "tLogRow_3";

				/**
				 * [tLogRow_3 finally ] stop
				 */

				/**
				 * [tSetGlobalVar_1 finally ] start
				 */

				currentComponent = "tSetGlobalVar_1";

				/**
				 * [tSetGlobalVar_1 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tRESTClient_1_SUBPROCESS_STATE", 1);
	}

	public static class datasetIdRowStruct implements routines.system.IPersistableRow<datasetIdRowStruct> {
		final static byte[] commonByteArrayLock_NSSOL_AddDatasetFromTDC_04 = new byte[0];
		static byte[] commonByteArray_NSSOL_AddDatasetFromTDC_04 = new byte[0];

		public String datasetId;

		public String getDatasetId() {
			return this.datasetId;
		}

		public String datasetName;

		public String getDatasetName() {
			return this.datasetName;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_NSSOL_AddDatasetFromTDC_04.length) {
					if (length < 1024 && commonByteArray_NSSOL_AddDatasetFromTDC_04.length == 0) {
						commonByteArray_NSSOL_AddDatasetFromTDC_04 = new byte[1024];
					} else {
						commonByteArray_NSSOL_AddDatasetFromTDC_04 = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_NSSOL_AddDatasetFromTDC_04, 0, length);
				strReturn = new String(commonByteArray_NSSOL_AddDatasetFromTDC_04, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_NSSOL_AddDatasetFromTDC_04) {

				try {

					int length = 0;

					this.datasetId = readString(dis);

					this.datasetName = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.datasetId, dos);

				// String

				writeString(this.datasetName, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("datasetId=" + datasetId);
			sb.append(",datasetName=" + datasetName);
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (datasetId == null) {
				sb.append("<null>");
			} else {
				sb.append(datasetId);
			}

			sb.append("|");

			if (datasetName == null) {
				sb.append("<null>");
			} else {
				sb.append(datasetName);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(datasetIdRowStruct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row9Struct implements routines.system.IPersistableRow<row9Struct> {
		final static byte[] commonByteArrayLock_NSSOL_AddDatasetFromTDC_04 = new byte[0];
		static byte[] commonByteArray_NSSOL_AddDatasetFromTDC_04 = new byte[0];

		public String datasetId;

		public String getDatasetId() {
			return this.datasetId;
		}

		public String datasetName;

		public String getDatasetName() {
			return this.datasetName;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_NSSOL_AddDatasetFromTDC_04.length) {
					if (length < 1024 && commonByteArray_NSSOL_AddDatasetFromTDC_04.length == 0) {
						commonByteArray_NSSOL_AddDatasetFromTDC_04 = new byte[1024];
					} else {
						commonByteArray_NSSOL_AddDatasetFromTDC_04 = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_NSSOL_AddDatasetFromTDC_04, 0, length);
				strReturn = new String(commonByteArray_NSSOL_AddDatasetFromTDC_04, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_NSSOL_AddDatasetFromTDC_04) {

				try {

					int length = 0;

					this.datasetId = readString(dis);

					this.datasetName = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.datasetId, dos);

				// String

				writeString(this.datasetName, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("datasetId=" + datasetId);
			sb.append(",datasetName=" + datasetName);
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (datasetId == null) {
				sb.append("<null>");
			} else {
				sb.append(datasetId);
			}

			sb.append("|");

			if (datasetName == null) {
				sb.append("<null>");
			} else {
				sb.append(datasetName);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row9Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row8Struct implements routines.system.IPersistableRow<row8Struct> {
		final static byte[] commonByteArrayLock_NSSOL_AddDatasetFromTDC_04 = new byte[0];
		static byte[] commonByteArray_NSSOL_AddDatasetFromTDC_04 = new byte[0];

		public Integer statusCode;

		public Integer getStatusCode() {
			return this.statusCode;
		}

		public routines.system.Document body;

		public routines.system.Document getBody() {
			return this.body;
		}

		public String string;

		public String getString() {
			return this.string;
		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_NSSOL_AddDatasetFromTDC_04.length) {
					if (length < 1024 && commonByteArray_NSSOL_AddDatasetFromTDC_04.length == 0) {
						commonByteArray_NSSOL_AddDatasetFromTDC_04 = new byte[1024];
					} else {
						commonByteArray_NSSOL_AddDatasetFromTDC_04 = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_NSSOL_AddDatasetFromTDC_04, 0, length);
				strReturn = new String(commonByteArray_NSSOL_AddDatasetFromTDC_04, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_NSSOL_AddDatasetFromTDC_04) {

				try {

					int length = 0;

					this.statusCode = readInteger(dis);

					this.body = (routines.system.Document) dis.readObject();

					this.string = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				} catch (ClassNotFoundException eCNFE) {
					throw new RuntimeException(eCNFE);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.statusCode, dos);

				// Document

				dos.writeObject(this.body);

				// String

				writeString(this.string, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("statusCode=" + String.valueOf(statusCode));
			sb.append(",body=" + String.valueOf(body));
			sb.append(",string=" + string);
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (statusCode == null) {
				sb.append("<null>");
			} else {
				sb.append(statusCode);
			}

			sb.append("|");

			if (body == null) {
				sb.append("<null>");
			} else {
				sb.append(body);
			}

			sb.append("|");

			if (string == null) {
				sb.append("<null>");
			} else {
				sb.append(string);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row8Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public void tRESTClient_3Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tRESTClient_3_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		String iterateId = "";

		String currentComponent = "";
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				row8Struct row8 = new row8Struct();
				row9Struct row9 = new row9Struct();
				row9Struct datasetIdRow = row9;

				/**
				 * [tSetGlobalVar_3 begin ] start
				 */

				ok_Hash.put("tSetGlobalVar_3", false);
				start_Hash.put("tSetGlobalVar_3", System.currentTimeMillis());

				currentComponent = "tSetGlobalVar_3";

				runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "datasetIdRow");

				int tos_count_tSetGlobalVar_3 = 0;

				if (log.isDebugEnabled())
					log.debug("tSetGlobalVar_3 - " + ("Start to work."));
				if (log.isDebugEnabled()) {
					class BytesLimit65535_tSetGlobalVar_3 {
						public void limitLog4jByte() throws Exception {
							StringBuilder log4jParamters_tSetGlobalVar_3 = new StringBuilder();
							log4jParamters_tSetGlobalVar_3.append("Parameters:");
							log4jParamters_tSetGlobalVar_3.append("VARIABLES" + " = " + "[{VALUE="
									+ ("datasetIdRow.datasetId") + ", KEY=" + ("\"varDatasetId\"") + "}]");
							log4jParamters_tSetGlobalVar_3.append(" | ");
							if (log.isDebugEnabled())
								log.debug("tSetGlobalVar_3 - " + (log4jParamters_tSetGlobalVar_3));
						}
					}
					new BytesLimit65535_tSetGlobalVar_3().limitLog4jByte();
				}
				if (enableLogStash) {
					talendJobLog.addCM("tSetGlobalVar_3", "tSetGlobalVar");
					talendJobLogProcess(globalMap);
				}

				/**
				 * [tSetGlobalVar_3 begin ] stop
				 */

				/**
				 * [tLogRow_2 begin ] start
				 */

				ok_Hash.put("tLogRow_2", false);
				start_Hash.put("tLogRow_2", System.currentTimeMillis());

				currentComponent = "tLogRow_2";

				runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "row9");

				int tos_count_tLogRow_2 = 0;

				if (log.isDebugEnabled())
					log.debug("tLogRow_2 - " + ("Start to work."));
				if (log.isDebugEnabled()) {
					class BytesLimit65535_tLogRow_2 {
						public void limitLog4jByte() throws Exception {
							StringBuilder log4jParamters_tLogRow_2 = new StringBuilder();
							log4jParamters_tLogRow_2.append("Parameters:");
							log4jParamters_tLogRow_2.append("BASIC_MODE" + " = " + "true");
							log4jParamters_tLogRow_2.append(" | ");
							log4jParamters_tLogRow_2.append("TABLE_PRINT" + " = " + "false");
							log4jParamters_tLogRow_2.append(" | ");
							log4jParamters_tLogRow_2.append("VERTICAL" + " = " + "false");
							log4jParamters_tLogRow_2.append(" | ");
							log4jParamters_tLogRow_2.append("FIELDSEPARATOR" + " = " + "\",\"");
							log4jParamters_tLogRow_2.append(" | ");
							log4jParamters_tLogRow_2.append("PRINT_HEADER" + " = " + "false");
							log4jParamters_tLogRow_2.append(" | ");
							log4jParamters_tLogRow_2.append("PRINT_UNIQUE_NAME" + " = " + "false");
							log4jParamters_tLogRow_2.append(" | ");
							log4jParamters_tLogRow_2.append("PRINT_COLNAMES" + " = " + "false");
							log4jParamters_tLogRow_2.append(" | ");
							log4jParamters_tLogRow_2.append("USE_FIXED_LENGTH" + " = " + "false");
							log4jParamters_tLogRow_2.append(" | ");
							log4jParamters_tLogRow_2.append("PRINT_CONTENT_WITH_LOG4J" + " = " + "true");
							log4jParamters_tLogRow_2.append(" | ");
							if (log.isDebugEnabled())
								log.debug("tLogRow_2 - " + (log4jParamters_tLogRow_2));
						}
					}
					new BytesLimit65535_tLogRow_2().limitLog4jByte();
				}
				if (enableLogStash) {
					talendJobLog.addCM("tLogRow_2", "tLogRow");
					talendJobLogProcess(globalMap);
				}

				///////////////////////

				final String OUTPUT_FIELD_SEPARATOR_tLogRow_2 = ",";
				java.io.PrintStream consoleOut_tLogRow_2 = null;

				StringBuilder strBuffer_tLogRow_2 = null;
				int nb_line_tLogRow_2 = 0;
///////////////////////    			

				/**
				 * [tLogRow_2 begin ] stop
				 */

				/**
				 * [tExtractJSONFields_3 begin ] start
				 */

				ok_Hash.put("tExtractJSONFields_3", false);
				start_Hash.put("tExtractJSONFields_3", System.currentTimeMillis());

				currentComponent = "tExtractJSONFields_3";

				runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "row8");

				int tos_count_tExtractJSONFields_3 = 0;

				if (log.isDebugEnabled())
					log.debug("tExtractJSONFields_3 - " + ("Start to work."));
				if (log.isDebugEnabled()) {
					class BytesLimit65535_tExtractJSONFields_3 {
						public void limitLog4jByte() throws Exception {
							StringBuilder log4jParamters_tExtractJSONFields_3 = new StringBuilder();
							log4jParamters_tExtractJSONFields_3.append("Parameters:");
							log4jParamters_tExtractJSONFields_3.append("READ_BY" + " = " + "JSONPATH");
							log4jParamters_tExtractJSONFields_3.append(" | ");
							log4jParamters_tExtractJSONFields_3.append("JSON_PATH_VERSION" + " = " + "2_1_0");
							log4jParamters_tExtractJSONFields_3.append(" | ");
							log4jParamters_tExtractJSONFields_3.append("JSONFIELD" + " = " + "string");
							log4jParamters_tExtractJSONFields_3.append(" | ");
							log4jParamters_tExtractJSONFields_3.append("JSON_LOOP_QUERY" + " = " + "\"$\"");
							log4jParamters_tExtractJSONFields_3.append(" | ");
							log4jParamters_tExtractJSONFields_3.append("MAPPING_4_JSONPATH" + " = " + "[{QUERY="
									+ ("\"$.id\"") + ", SCHEMA_COLUMN=" + ("datasetId") + "}, {QUERY=" + ("\"$.name\"")
									+ ", SCHEMA_COLUMN=" + ("datasetName") + "}]");
							log4jParamters_tExtractJSONFields_3.append(" | ");
							log4jParamters_tExtractJSONFields_3.append("DIE_ON_ERROR" + " = " + "false");
							log4jParamters_tExtractJSONFields_3.append(" | ");
							log4jParamters_tExtractJSONFields_3.append("USE_LOOP_AS_ROOT" + " = " + "true");
							log4jParamters_tExtractJSONFields_3.append(" | ");
							if (log.isDebugEnabled())
								log.debug("tExtractJSONFields_3 - " + (log4jParamters_tExtractJSONFields_3));
						}
					}
					new BytesLimit65535_tExtractJSONFields_3().limitLog4jByte();
				}
				if (enableLogStash) {
					talendJobLog.addCM("tExtractJSONFields_3", "tExtractJSONFields");
					talendJobLogProcess(globalMap);
				}

				int nb_line_tExtractJSONFields_3 = 0;
				String jsonStr_tExtractJSONFields_3 = "";

				class JsonPathCache_tExtractJSONFields_3 {
					final java.util.Map<String, com.jayway.jsonpath.JsonPath> jsonPathString2compiledJsonPath = new java.util.HashMap<String, com.jayway.jsonpath.JsonPath>();

					public com.jayway.jsonpath.JsonPath getCompiledJsonPath(String jsonPath) {
						if (jsonPathString2compiledJsonPath.containsKey(jsonPath)) {
							return jsonPathString2compiledJsonPath.get(jsonPath);
						} else {
							com.jayway.jsonpath.JsonPath compiledLoopPath = com.jayway.jsonpath.JsonPath
									.compile(jsonPath);
							jsonPathString2compiledJsonPath.put(jsonPath, compiledLoopPath);
							return compiledLoopPath;
						}
					}
				}

				JsonPathCache_tExtractJSONFields_3 jsonPathCache_tExtractJSONFields_3 = new JsonPathCache_tExtractJSONFields_3();

				/**
				 * [tExtractJSONFields_3 begin ] stop
				 */

				/**
				 * [tRESTClient_3 begin ] start
				 */

				ok_Hash.put("tRESTClient_3", false);
				start_Hash.put("tRESTClient_3", System.currentTimeMillis());

				currentComponent = "tRESTClient_3";

				int tos_count_tRESTClient_3 = 0;

				if (enableLogStash) {
					talendJobLog.addCM("tRESTClient_3", "tRESTClient");
					talendJobLogProcess(globalMap);
				}

				/**
				 * [tRESTClient_3 begin ] stop
				 */

				/**
				 * [tRESTClient_3 main ] start
				 */

				currentComponent = "tRESTClient_3";

				row8 = null;

// expected response body
				Object responseDoc_tRESTClient_3 = null;

				try {
					// request body
					org.dom4j.Document requestDoc_tRESTClient_3 = null;
					String requestString_tRESTClient_3 = null;

					Object requestBody_tRESTClient_3 = requestDoc_tRESTClient_3 != null ? requestDoc_tRESTClient_3
							: requestString_tRESTClient_3;

					// resposne class name
					Class<?> responseClass_tRESTClient_3 = String.class;

					// create web client instance
					org.apache.cxf.jaxrs.client.JAXRSClientFactoryBean factoryBean_tRESTClient_3 = new org.apache.cxf.jaxrs.client.JAXRSClientFactoryBean();

					boolean inOSGi = routines.system.BundleUtils.inOSGi();

					final java.util.List<org.apache.cxf.feature.Feature> features_tRESTClient_3 = new java.util.ArrayList<org.apache.cxf.feature.Feature>();

					String url = "https://tdp.ap.cloud.talend.com/api/datasets";
					// {baseUri}tRESTClient
					factoryBean_tRESTClient_3.setServiceName(new javax.xml.namespace.QName(url, "tRESTClient"));
					factoryBean_tRESTClient_3.setAddress(url);

					factoryBean_tRESTClient_3.setFeatures(features_tRESTClient_3);

					java.util.List<Object> providers_tRESTClient_3 = new java.util.ArrayList<Object>();
					providers_tRESTClient_3.add(new org.apache.cxf.jaxrs.provider.dom4j.DOM4JProvider() {
						// workaround for https://jira.talendforge.org/browse/TESB-7276
						public org.dom4j.Document readFrom(Class<org.dom4j.Document> cls, java.lang.reflect.Type type,
								java.lang.annotation.Annotation[] anns, javax.ws.rs.core.MediaType mt,
								javax.ws.rs.core.MultivaluedMap<String, String> headers, java.io.InputStream is)
								throws IOException, javax.ws.rs.WebApplicationException {
							String contentLength = headers.getFirst("Content-Length");
							if (!org.apache.cxf.common.util.StringUtils.isEmpty(contentLength)
									&& Integer.valueOf(contentLength) <= 0) {
								try {
									return org.dom4j.DocumentHelper.parseText("<root/>");
								} catch (org.dom4j.DocumentException e_tRESTClient_3) {
									e_tRESTClient_3.printStackTrace();
								}
								return null;
							}
							return super.readFrom(cls, type, anns, mt, headers, is);
						}
					});
					org.apache.cxf.jaxrs.provider.json.JSONProvider jsonProvider_tRESTClient_3 = new org.apache.cxf.jaxrs.provider.json.JSONProvider();
					jsonProvider_tRESTClient_3.setIgnoreNamespaces(true);
					jsonProvider_tRESTClient_3.setAttributesToElements(true);

					jsonProvider_tRESTClient_3.setSupportUnwrapped(true);
					jsonProvider_tRESTClient_3.setWrapperName("root");

					jsonProvider_tRESTClient_3.setDropRootElement(false);
					jsonProvider_tRESTClient_3.setConvertTypesToStrings(false);
					providers_tRESTClient_3.add(jsonProvider_tRESTClient_3);
					factoryBean_tRESTClient_3.setProviders(providers_tRESTClient_3);
					factoryBean_tRESTClient_3.setTransportId("http://cxf.apache.org/transports/http");

					boolean use_auth_tRESTClient_3 = true;

					if (use_auth_tRESTClient_3 && "SAML".equals("OAUTH2_BEARER")) {
						if (!inOSGi) {
							throw new IllegalArgumentException(
									"SAML based security scenarios are not supported in Studio (standalone)");
						}
						// set SAML Token authentication

						final String decryptedPassword_tRESTClient_3 = routines.system.PasswordEncryptUtil
								.decryptPassword(
										"enc:routine.encryption.key.v1:AJ6zxVKX3t9GkVly4EJ891OMDMm/tXMpdTkFRGXYmNXTh/ko");

						org.apache.cxf.ws.security.trust.STSClient stsClient = org.talend.esb.security.saml.STSClientUtils
								.createSTSClient(factoryBean_tRESTClient_3.getBus(), "username",
										decryptedPassword_tRESTClient_3);

						org.talend.esb.security.saml.SAMLRESTUtils.configureClient(factoryBean_tRESTClient_3,
								stsClient);
					}

					org.apache.cxf.jaxrs.client.WebClient webClient_tRESTClient_3 = factoryBean_tRESTClient_3
							.createWebClient();

					// set request path
					webClient_tRESTClient_3.path("");

					// set connection properties
					org.apache.cxf.jaxrs.client.ClientConfiguration clientConfig_tRESTClient_3 = org.apache.cxf.jaxrs.client.WebClient
							.getConfig(webClient_tRESTClient_3);
					org.apache.cxf.transport.http.HTTPConduit conduit_tRESTClient_3 = clientConfig_tRESTClient_3
							.getHttpConduit();

					if (clientConfig_tRESTClient_3.getEndpoint() != null
							&& clientConfig_tRESTClient_3.getEndpoint().getEndpointInfo() != null) {
						clientConfig_tRESTClient_3.getEndpoint().getEndpointInfo()
								.setProperty("enable.webclient.operation.reporting", true);
					}

					if (use_auth_tRESTClient_3 && "BASIC".equals("OAUTH2_BEARER")) {
						// set BASIC auth

						final String decryptedPassword_tRESTClient_3 = routines.system.PasswordEncryptUtil
								.decryptPassword(
										"enc:routine.encryption.key.v1:I1NYMULX6cLZQN+/1n+Y3IpFin7kO+NIllo1l2N1gL83r+1S");

						org.apache.cxf.configuration.security.AuthorizationPolicy authPolicy_tRESTClient_3 = new org.apache.cxf.configuration.security.AuthorizationPolicy();
						authPolicy_tRESTClient_3.setAuthorizationType("Basic");
						authPolicy_tRESTClient_3.setUserName("username");
						authPolicy_tRESTClient_3.setPassword(decryptedPassword_tRESTClient_3);
						conduit_tRESTClient_3.setAuthorization(authPolicy_tRESTClient_3);
					} else if (use_auth_tRESTClient_3 && "HTTP Digest".equals("OAUTH2_BEARER")) {
						// set Digest auth

						final String decryptedPassword_tRESTClient_3 = routines.system.PasswordEncryptUtil
								.decryptPassword(
										"enc:routine.encryption.key.v1:J+SkUlsMSzeI9PDkiDkpT9pCL7aCWfz+wUZ8Evh6kZM8QzA1");

						org.apache.cxf.configuration.security.AuthorizationPolicy authPolicy_tRESTClient_3 = new org.apache.cxf.configuration.security.AuthorizationPolicy();
						authPolicy_tRESTClient_3.setAuthorizationType("Digest");
						authPolicy_tRESTClient_3.setUserName("username");
						authPolicy_tRESTClient_3.setPassword(decryptedPassword_tRESTClient_3);
						conduit_tRESTClient_3.setAuthorization(authPolicy_tRESTClient_3);
					}

					if (!inOSGi) {
						conduit_tRESTClient_3.getClient().setReceiveTimeout((long) (60 * 1000L));
						conduit_tRESTClient_3.getClient().setConnectionTimeout((long) (30 * 1000L));
						boolean use_proxy_tRESTClient_3 = false;

					}

					// set Accept-Type
					webClient_tRESTClient_3.accept("application/json");

					// set optional query and header properties if any

					if (use_auth_tRESTClient_3 && "OAUTH2_BEARER".equals("OAUTH2_BEARER")) {
						// set oAuth2 bearer token
						webClient_tRESTClient_3.header("Authorization",
								"Bearer " + "N1Hi0zFKRxie79flQEBmNubF8okwmsQgP1Eyhb93Slz8MKgtRVKQQXXdN0_PZQRP");
					}

					if (use_auth_tRESTClient_3 && "OIDC_PASSWORD_GRANT".equals("OAUTH2_BEARER")) {

						final String decryptedPassword_tRESTClient_3 = routines.system.PasswordEncryptUtil
								.decryptPassword(
										"enc:routine.encryption.key.v1:pEwrFGw9dZLO1jBiH3MXmMg9LfwA863Fc+NyC9qz/E4c1Vyu");

						String username_tRESTClient_3 = "username";
						String password_tRESTClient_3 = decryptedPassword_tRESTClient_3;

						String bearerHeader_tRESTClient_3 = null;

						try {
							if (!inOSGi) {
								org.springframework.context.support.ClassPathXmlApplicationContext context_tRESTClient_3 = new org.springframework.context.support.ClassPathXmlApplicationContext(
										"META-INF/tesb/oidc-context.xml");
								org.talend.esb.security.oidc.OidcConfiguration oidcConfiguration_tRESTClient_3 = context_tRESTClient_3
										.getBean(org.talend.esb.security.oidc.OidcConfiguration.class);
								bearerHeader_tRESTClient_3 = org.talend.esb.security.oidc.OidcClientUtils
										.oidcClientBearer(username_tRESTClient_3, password_tRESTClient_3,
												oidcConfiguration_tRESTClient_3);
							} else {
								bearerHeader_tRESTClient_3 = org.talend.esb.security.oidc.OidcClientUtils
										.oidcClientBearer(username_tRESTClient_3, password_tRESTClient_3);
							}
						} catch (Exception ex) {
							throw new javax.ws.rs.WebApplicationException("Failed to get OIDC access token: ", ex);
						}

						webClient_tRESTClient_3.header("Authorization", bearerHeader_tRESTClient_3);
					}

					// if FORM request then capture query parameters into Form, otherwise set them
					// as queries

					webClient_tRESTClient_3.query("name", context.tdpDatasetName);

					try {
						// start send request

						responseDoc_tRESTClient_3 = webClient_tRESTClient_3.get(responseClass_tRESTClient_3);

						int webClientResponseStatus_tRESTClient_3 = webClient_tRESTClient_3.getResponse().getStatus();
						if (webClientResponseStatus_tRESTClient_3 >= 300) {
							throw new javax.ws.rs.WebApplicationException(webClient_tRESTClient_3.getResponse());
						}

						if (row8 == null) {
							row8 = new row8Struct();
						}

						row8.statusCode = webClientResponseStatus_tRESTClient_3;

						{
							Object responseObj_tRESTClient_3 = responseDoc_tRESTClient_3;

							if (responseObj_tRESTClient_3 != null) {
								if (responseClass_tRESTClient_3 == String.class) {
									row8.string = (String) responseObj_tRESTClient_3;
								} else {
									routines.system.Document responseTalendDoc_tRESTClient_3 = null;
									if (null != responseObj_tRESTClient_3) {
										responseTalendDoc_tRESTClient_3 = new routines.system.Document();
										responseTalendDoc_tRESTClient_3
												.setDocument((org.dom4j.Document) responseObj_tRESTClient_3);
									}
									row8.body = responseTalendDoc_tRESTClient_3;
								}
							}
						}

						globalMap.put("tRESTClient_3_HEADERS", webClient_tRESTClient_3.getResponse().getHeaders());

					} catch (javax.ws.rs.WebApplicationException ex_tRESTClient_3) {

						throw ex_tRESTClient_3;

					}

				} catch (Exception e_tRESTClient_3) {

					throw new TalendException(e_tRESTClient_3, currentComponent, globalMap);

				}

				tos_count_tRESTClient_3++;

				/**
				 * [tRESTClient_3 main ] stop
				 */

				/**
				 * [tRESTClient_3 process_data_begin ] start
				 */

				currentComponent = "tRESTClient_3";

				/**
				 * [tRESTClient_3 process_data_begin ] stop
				 */
// Start of branch "row8"
				if (row8 != null) {

					/**
					 * [tExtractJSONFields_3 main ] start
					 */

					currentComponent = "tExtractJSONFields_3";

					runStat.updateStatAndLog(execStat, enableLogStash, iterateId, 1, 1, "row8");

					if (log.isTraceEnabled()) {
						log.trace("row8 - " + (row8 == null ? "" : row8.toLogString()));
					}

					if (row8.string != null) {// C_01
						jsonStr_tExtractJSONFields_3 = row8.string.toString();

						row9 = null;

						String loopPath_tExtractJSONFields_3 = "$";
						java.util.List<Object> resultset_tExtractJSONFields_3 = new java.util.ArrayList<Object>();

						boolean isStructError_tExtractJSONFields_3 = true;
						com.jayway.jsonpath.ReadContext document_tExtractJSONFields_3 = null;
						try {
							document_tExtractJSONFields_3 = com.jayway.jsonpath.JsonPath
									.parse(jsonStr_tExtractJSONFields_3);
							com.jayway.jsonpath.JsonPath compiledLoopPath_tExtractJSONFields_3 = jsonPathCache_tExtractJSONFields_3
									.getCompiledJsonPath(loopPath_tExtractJSONFields_3);
							Object result_tExtractJSONFields_3 = document_tExtractJSONFields_3
									.read(compiledLoopPath_tExtractJSONFields_3, net.minidev.json.JSONObject.class);
							if (result_tExtractJSONFields_3 instanceof net.minidev.json.JSONArray) {
								resultset_tExtractJSONFields_3 = (net.minidev.json.JSONArray) result_tExtractJSONFields_3;
							} else {
								resultset_tExtractJSONFields_3.add(result_tExtractJSONFields_3);
							}

							isStructError_tExtractJSONFields_3 = false;
						} catch (java.lang.Exception ex_tExtractJSONFields_3) {
							log.error("tExtractJSONFields_3 - " + ex_tExtractJSONFields_3.getMessage());
							System.err.println(ex_tExtractJSONFields_3.getMessage());
						}

						String jsonPath_tExtractJSONFields_3 = null;
						com.jayway.jsonpath.JsonPath compiledJsonPath_tExtractJSONFields_3 = null;

						Object value_tExtractJSONFields_3 = null;

						Object root_tExtractJSONFields_3 = null;
						for (int i_tExtractJSONFields_3 = 0; isStructError_tExtractJSONFields_3
								|| (i_tExtractJSONFields_3 < resultset_tExtractJSONFields_3
										.size()); i_tExtractJSONFields_3++) {
							if (!isStructError_tExtractJSONFields_3) {
								Object row_tExtractJSONFields_3 = resultset_tExtractJSONFields_3
										.get(i_tExtractJSONFields_3);
								row9 = null;
								row9 = new row9Struct();
								nb_line_tExtractJSONFields_3++;
								try {
									jsonPath_tExtractJSONFields_3 = "$.id";
									compiledJsonPath_tExtractJSONFields_3 = jsonPathCache_tExtractJSONFields_3
											.getCompiledJsonPath(jsonPath_tExtractJSONFields_3);

									try {

										value_tExtractJSONFields_3 = compiledJsonPath_tExtractJSONFields_3
												.read(row_tExtractJSONFields_3);

										row9.datasetId = value_tExtractJSONFields_3 == null ?

												null

												: value_tExtractJSONFields_3.toString();
									} catch (com.jayway.jsonpath.PathNotFoundException e_tExtractJSONFields_3) {
										row9.datasetId =

												null

										;
									}
									jsonPath_tExtractJSONFields_3 = "$.name";
									compiledJsonPath_tExtractJSONFields_3 = jsonPathCache_tExtractJSONFields_3
											.getCompiledJsonPath(jsonPath_tExtractJSONFields_3);

									try {

										value_tExtractJSONFields_3 = compiledJsonPath_tExtractJSONFields_3
												.read(row_tExtractJSONFields_3);

										row9.datasetName = value_tExtractJSONFields_3 == null ?

												null

												: value_tExtractJSONFields_3.toString();
									} catch (com.jayway.jsonpath.PathNotFoundException e_tExtractJSONFields_3) {
										row9.datasetName =

												null

										;
									}
								} catch (java.lang.Exception ex_tExtractJSONFields_3) {
									log.error("tExtractJSONFields_3 - " + ex_tExtractJSONFields_3.getMessage());
									System.err.println(ex_tExtractJSONFields_3.getMessage());
									row9 = null;
								}

							}

							isStructError_tExtractJSONFields_3 = false;

							log.debug("tExtractJSONFields_3 - Extracting the record " + nb_line_tExtractJSONFields_3
									+ ".");
//}

							tos_count_tExtractJSONFields_3++;

							/**
							 * [tExtractJSONFields_3 main ] stop
							 */

							/**
							 * [tExtractJSONFields_3 process_data_begin ] start
							 */

							currentComponent = "tExtractJSONFields_3";

							/**
							 * [tExtractJSONFields_3 process_data_begin ] stop
							 */
// Start of branch "row9"
							if (row9 != null) {

								/**
								 * [tLogRow_2 main ] start
								 */

								currentComponent = "tLogRow_2";

								runStat.updateStatAndLog(execStat, enableLogStash, iterateId, 1, 1, "row9");

								if (log.isTraceEnabled()) {
									log.trace("row9 - " + (row9 == null ? "" : row9.toLogString()));
								}

///////////////////////		

								strBuffer_tLogRow_2 = new StringBuilder();

								if (row9.datasetId != null) { //

									strBuffer_tLogRow_2.append(String.valueOf(row9.datasetId));

								} //

								strBuffer_tLogRow_2.append(",");

								if (row9.datasetName != null) { //

									strBuffer_tLogRow_2.append(String.valueOf(row9.datasetName));

								} //

								if (globalMap.get("tLogRow_CONSOLE") != null) {
									consoleOut_tLogRow_2 = (java.io.PrintStream) globalMap.get("tLogRow_CONSOLE");
								} else {
									consoleOut_tLogRow_2 = new java.io.PrintStream(
											new java.io.BufferedOutputStream(System.out));
									globalMap.put("tLogRow_CONSOLE", consoleOut_tLogRow_2);
								}
								log.info("tLogRow_2 - Content of row " + (nb_line_tLogRow_2 + 1) + ": "
										+ strBuffer_tLogRow_2.toString());
								consoleOut_tLogRow_2.println(strBuffer_tLogRow_2.toString());
								consoleOut_tLogRow_2.flush();
								nb_line_tLogRow_2++;
//////

//////                    

///////////////////////    			

								datasetIdRow = row9;

								tos_count_tLogRow_2++;

								/**
								 * [tLogRow_2 main ] stop
								 */

								/**
								 * [tLogRow_2 process_data_begin ] start
								 */

								currentComponent = "tLogRow_2";

								/**
								 * [tLogRow_2 process_data_begin ] stop
								 */

								/**
								 * [tSetGlobalVar_3 main ] start
								 */

								currentComponent = "tSetGlobalVar_3";

								runStat.updateStatAndLog(execStat, enableLogStash, iterateId, 1, 1, "datasetIdRow");

								if (log.isTraceEnabled()) {
									log.trace("datasetIdRow - "
											+ (datasetIdRow == null ? "" : datasetIdRow.toLogString()));
								}

								globalMap.put("varDatasetId", datasetIdRow.datasetId);

								tos_count_tSetGlobalVar_3++;

								/**
								 * [tSetGlobalVar_3 main ] stop
								 */

								/**
								 * [tSetGlobalVar_3 process_data_begin ] start
								 */

								currentComponent = "tSetGlobalVar_3";

								/**
								 * [tSetGlobalVar_3 process_data_begin ] stop
								 */

								/**
								 * [tSetGlobalVar_3 process_data_end ] start
								 */

								currentComponent = "tSetGlobalVar_3";

								/**
								 * [tSetGlobalVar_3 process_data_end ] stop
								 */

								/**
								 * [tLogRow_2 process_data_end ] start
								 */

								currentComponent = "tLogRow_2";

								/**
								 * [tLogRow_2 process_data_end ] stop
								 */

							} // End of branch "row9"

							// end for
						}

					} // C_01

					/**
					 * [tExtractJSONFields_3 process_data_end ] start
					 */

					currentComponent = "tExtractJSONFields_3";

					/**
					 * [tExtractJSONFields_3 process_data_end ] stop
					 */

				} // End of branch "row8"

				/**
				 * [tRESTClient_3 process_data_end ] start
				 */

				currentComponent = "tRESTClient_3";

				/**
				 * [tRESTClient_3 process_data_end ] stop
				 */

				/**
				 * [tRESTClient_3 end ] start
				 */

				currentComponent = "tRESTClient_3";

				if (globalMap.get("tRESTClient_3_NB_LINE") == null) {
					globalMap.put("tRESTClient_3_NB_LINE", 1);
				}

// [tRESTCliend_end]

				ok_Hash.put("tRESTClient_3", true);
				end_Hash.put("tRESTClient_3", System.currentTimeMillis());

				/**
				 * [tRESTClient_3 end ] stop
				 */

				/**
				 * [tExtractJSONFields_3 end ] start
				 */

				currentComponent = "tExtractJSONFields_3";

				globalMap.put("tExtractJSONFields_3_NB_LINE", nb_line_tExtractJSONFields_3);
				log.debug("tExtractJSONFields_3 - Extracted records count: " + nb_line_tExtractJSONFields_3 + " .");

				if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "row8", 2, 0,
						talendJobLog, "tRESTClient_3", "tRESTClient", "tExtractJSONFields_3", "tExtractJSONFields",
						"output")) {
					talendJobLogProcess(globalMap);
				}

				if (log.isDebugEnabled())
					log.debug("tExtractJSONFields_3 - " + ("Done."));

				ok_Hash.put("tExtractJSONFields_3", true);
				end_Hash.put("tExtractJSONFields_3", System.currentTimeMillis());

				/**
				 * [tExtractJSONFields_3 end ] stop
				 */

				/**
				 * [tLogRow_2 end ] start
				 */

				currentComponent = "tLogRow_2";

//////
//////
				globalMap.put("tLogRow_2_NB_LINE", nb_line_tLogRow_2);
				if (log.isInfoEnabled())
					log.info("tLogRow_2 - " + ("Printed row count: ") + (nb_line_tLogRow_2) + ("."));

///////////////////////    			

				if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "row9", 2, 0,
						talendJobLog, "tExtractJSONFields_3", "tExtractJSONFields", "tLogRow_2", "tLogRow", "output")) {
					talendJobLogProcess(globalMap);
				}

				if (log.isDebugEnabled())
					log.debug("tLogRow_2 - " + ("Done."));

				ok_Hash.put("tLogRow_2", true);
				end_Hash.put("tLogRow_2", System.currentTimeMillis());

				/**
				 * [tLogRow_2 end ] stop
				 */

				/**
				 * [tSetGlobalVar_3 end ] start
				 */

				currentComponent = "tSetGlobalVar_3";

				if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "datasetIdRow", 2, 0,
						talendJobLog, "tLogRow_2", "tLogRow", "tSetGlobalVar_3", "tSetGlobalVar", "output")) {
					talendJobLogProcess(globalMap);
				}

				if (log.isDebugEnabled())
					log.debug("tSetGlobalVar_3 - " + ("Done."));

				ok_Hash.put("tSetGlobalVar_3", true);
				end_Hash.put("tSetGlobalVar_3", System.currentTimeMillis());

				/**
				 * [tSetGlobalVar_3 end ] stop
				 */

			} // end the resume

			if (resumeEntryMethodName == null || globalResumeTicket) {
				resumeUtil.addLog("CHECKPOINT", "CONNECTION:SUBJOB_OK:tRESTClient_3:OnSubjobOk", "",
						Thread.currentThread().getId() + "", "", "", "", "", "");
			}

			if (execStat) {
				runStat.updateStatOnConnection("OnSubjobOk2", 0, "ok");
			}

			tRESTClient_2Process(globalMap);

		} catch (java.lang.Exception e) {

			if (!(e instanceof TalendException)) {
				log.fatal(currentComponent + " " + e.getMessage(), e);
			}

			TalendException te = new TalendException(e, currentComponent, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			try {

				/**
				 * [tRESTClient_3 finally ] start
				 */

				currentComponent = "tRESTClient_3";

				/**
				 * [tRESTClient_3 finally ] stop
				 */

				/**
				 * [tExtractJSONFields_3 finally ] start
				 */

				currentComponent = "tExtractJSONFields_3";

				/**
				 * [tExtractJSONFields_3 finally ] stop
				 */

				/**
				 * [tLogRow_2 finally ] start
				 */

				currentComponent = "tLogRow_2";

				/**
				 * [tLogRow_2 finally ] stop
				 */

				/**
				 * [tSetGlobalVar_3 finally ] start
				 */

				currentComponent = "tSetGlobalVar_3";

				/**
				 * [tSetGlobalVar_3 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tRESTClient_3_SUBPROCESS_STATE", 1);
	}

	public static class map1Struct implements routines.system.IPersistableRow<map1Struct> {
		final static byte[] commonByteArrayLock_NSSOL_AddDatasetFromTDC_04 = new byte[0];
		static byte[] commonByteArray_NSSOL_AddDatasetFromTDC_04 = new byte[0];

		public routines.system.Document body;

		public routines.system.Document getBody() {
			return this.body;
		}

		public String string;

		public String getString() {
			return this.string;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_NSSOL_AddDatasetFromTDC_04.length) {
					if (length < 1024 && commonByteArray_NSSOL_AddDatasetFromTDC_04.length == 0) {
						commonByteArray_NSSOL_AddDatasetFromTDC_04 = new byte[1024];
					} else {
						commonByteArray_NSSOL_AddDatasetFromTDC_04 = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_NSSOL_AddDatasetFromTDC_04, 0, length);
				strReturn = new String(commonByteArray_NSSOL_AddDatasetFromTDC_04, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_NSSOL_AddDatasetFromTDC_04) {

				try {

					int length = 0;

					this.body = (routines.system.Document) dis.readObject();

					this.string = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				} catch (ClassNotFoundException eCNFE) {
					throw new RuntimeException(eCNFE);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Document

				dos.writeObject(this.body);

				// String

				writeString(this.string, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("body=" + String.valueOf(body));
			sb.append(",string=" + string);
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (body == null) {
				sb.append("<null>");
			} else {
				sb.append(body);
			}

			sb.append("|");

			if (string == null) {
				sb.append("<null>");
			} else {
				sb.append(string);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(map1Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row10Struct implements routines.system.IPersistableRow<row10Struct> {
		final static byte[] commonByteArrayLock_NSSOL_AddDatasetFromTDC_04 = new byte[0];
		static byte[] commonByteArray_NSSOL_AddDatasetFromTDC_04 = new byte[0];

		public String groupId;

		public String getGroupId() {
			return this.groupId;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_NSSOL_AddDatasetFromTDC_04.length) {
					if (length < 1024 && commonByteArray_NSSOL_AddDatasetFromTDC_04.length == 0) {
						commonByteArray_NSSOL_AddDatasetFromTDC_04 = new byte[1024];
					} else {
						commonByteArray_NSSOL_AddDatasetFromTDC_04 = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_NSSOL_AddDatasetFromTDC_04, 0, length);
				strReturn = new String(commonByteArray_NSSOL_AddDatasetFromTDC_04, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_NSSOL_AddDatasetFromTDC_04) {

				try {

					int length = 0;

					this.groupId = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.groupId, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("groupId=" + groupId);
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (groupId == null) {
				sb.append("<null>");
			} else {
				sb.append(groupId);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row10Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row7Struct implements routines.system.IPersistableRow<row7Struct> {
		final static byte[] commonByteArrayLock_NSSOL_AddDatasetFromTDC_04 = new byte[0];
		static byte[] commonByteArray_NSSOL_AddDatasetFromTDC_04 = new byte[0];

		public String groupId;

		public String getGroupId() {
			return this.groupId;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_NSSOL_AddDatasetFromTDC_04.length) {
					if (length < 1024 && commonByteArray_NSSOL_AddDatasetFromTDC_04.length == 0) {
						commonByteArray_NSSOL_AddDatasetFromTDC_04 = new byte[1024];
					} else {
						commonByteArray_NSSOL_AddDatasetFromTDC_04 = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_NSSOL_AddDatasetFromTDC_04, 0, length);
				strReturn = new String(commonByteArray_NSSOL_AddDatasetFromTDC_04, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_NSSOL_AddDatasetFromTDC_04) {

				try {

					int length = 0;

					this.groupId = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.groupId, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("groupId=" + groupId);
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (groupId == null) {
				sb.append("<null>");
			} else {
				sb.append(groupId);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row7Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row6Struct implements routines.system.IPersistableRow<row6Struct> {
		final static byte[] commonByteArrayLock_NSSOL_AddDatasetFromTDC_04 = new byte[0];
		static byte[] commonByteArray_NSSOL_AddDatasetFromTDC_04 = new byte[0];

		public String groupId;

		public String getGroupId() {
			return this.groupId;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_NSSOL_AddDatasetFromTDC_04.length) {
					if (length < 1024 && commonByteArray_NSSOL_AddDatasetFromTDC_04.length == 0) {
						commonByteArray_NSSOL_AddDatasetFromTDC_04 = new byte[1024];
					} else {
						commonByteArray_NSSOL_AddDatasetFromTDC_04 = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_NSSOL_AddDatasetFromTDC_04, 0, length);
				strReturn = new String(commonByteArray_NSSOL_AddDatasetFromTDC_04, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_NSSOL_AddDatasetFromTDC_04) {

				try {

					int length = 0;

					this.groupId = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.groupId, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("groupId=" + groupId);
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (groupId == null) {
				sb.append("<null>");
			} else {
				sb.append(groupId);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row6Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row4Struct implements routines.system.IPersistableRow<row4Struct> {
		final static byte[] commonByteArrayLock_NSSOL_AddDatasetFromTDC_04 = new byte[0];
		static byte[] commonByteArray_NSSOL_AddDatasetFromTDC_04 = new byte[0];

		public Integer statusCode;

		public Integer getStatusCode() {
			return this.statusCode;
		}

		public routines.system.Document body;

		public routines.system.Document getBody() {
			return this.body;
		}

		public String string;

		public String getString() {
			return this.string;
		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_NSSOL_AddDatasetFromTDC_04.length) {
					if (length < 1024 && commonByteArray_NSSOL_AddDatasetFromTDC_04.length == 0) {
						commonByteArray_NSSOL_AddDatasetFromTDC_04 = new byte[1024];
					} else {
						commonByteArray_NSSOL_AddDatasetFromTDC_04 = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_NSSOL_AddDatasetFromTDC_04, 0, length);
				strReturn = new String(commonByteArray_NSSOL_AddDatasetFromTDC_04, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_NSSOL_AddDatasetFromTDC_04) {

				try {

					int length = 0;

					this.statusCode = readInteger(dis);

					this.body = (routines.system.Document) dis.readObject();

					this.string = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				} catch (ClassNotFoundException eCNFE) {
					throw new RuntimeException(eCNFE);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.statusCode, dos);

				// Document

				dos.writeObject(this.body);

				// String

				writeString(this.string, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("statusCode=" + String.valueOf(statusCode));
			sb.append(",body=" + String.valueOf(body));
			sb.append(",string=" + string);
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (statusCode == null) {
				sb.append("<null>");
			} else {
				sb.append(statusCode);
			}

			sb.append("|");

			if (body == null) {
				sb.append("<null>");
			} else {
				sb.append(body);
			}

			sb.append("|");

			if (string == null) {
				sb.append("<null>");
			} else {
				sb.append(string);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row4Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public void tRESTClient_2Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tRESTClient_2_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		String iterateId = "";

		String currentComponent = "";
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				row4Struct row4 = new row4Struct();
				row6Struct row6 = new row6Struct();
				row6Struct row7 = row6;
				row6Struct row10 = row6;
				map1Struct map1 = new map1Struct();

				/**
				 * [tRESTClient_4 begin ] start
				 */

				ok_Hash.put("tRESTClient_4", false);
				start_Hash.put("tRESTClient_4", System.currentTimeMillis());

				currentComponent = "tRESTClient_4";

				runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "map1");

				int tos_count_tRESTClient_4 = 0;

				if (enableLogStash) {
					talendJobLog.addCM("tRESTClient_4", "tRESTClient");
					talendJobLogProcess(globalMap);
				}

				/**
				 * [tRESTClient_4 begin ] stop
				 */

				/**
				 * [tMap_1 begin ] start
				 */

				ok_Hash.put("tMap_1", false);
				start_Hash.put("tMap_1", System.currentTimeMillis());

				currentComponent = "tMap_1";

				runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "row10");

				int tos_count_tMap_1 = 0;

				if (log.isDebugEnabled())
					log.debug("tMap_1 - " + ("Start to work."));
				if (log.isDebugEnabled()) {
					class BytesLimit65535_tMap_1 {
						public void limitLog4jByte() throws Exception {
							StringBuilder log4jParamters_tMap_1 = new StringBuilder();
							log4jParamters_tMap_1.append("Parameters:");
							log4jParamters_tMap_1.append("LINK_STYLE" + " = " + "AUTO");
							log4jParamters_tMap_1.append(" | ");
							log4jParamters_tMap_1.append("TEMPORARY_DATA_DIRECTORY" + " = " + "");
							log4jParamters_tMap_1.append(" | ");
							log4jParamters_tMap_1.append("ROWS_BUFFER_SIZE" + " = " + "2000000");
							log4jParamters_tMap_1.append(" | ");
							log4jParamters_tMap_1.append("CHANGE_HASH_AND_EQUALS_FOR_BIGDECIMAL" + " = " + "true");
							log4jParamters_tMap_1.append(" | ");
							if (log.isDebugEnabled())
								log.debug("tMap_1 - " + (log4jParamters_tMap_1));
						}
					}
					new BytesLimit65535_tMap_1().limitLog4jByte();
				}
				if (enableLogStash) {
					talendJobLog.addCM("tMap_1", "tMap");
					talendJobLogProcess(globalMap);
				}

// ###############################
// # Lookup's keys initialization
				int count_row10_tMap_1 = 0;

// ###############################        

// ###############################
// # Vars initialization
				class Var__tMap_1__Struct {
				}
				Var__tMap_1__Struct Var__tMap_1 = new Var__tMap_1__Struct();
// ###############################

// ###############################
// # Outputs initialization
				int count_map1_tMap_1 = 0;

				map1Struct map1_tmp = new map1Struct();
// ###############################

				/**
				 * [tMap_1 begin ] stop
				 */

				/**
				 * [tSetGlobalVar_2 begin ] start
				 */

				ok_Hash.put("tSetGlobalVar_2", false);
				start_Hash.put("tSetGlobalVar_2", System.currentTimeMillis());

				currentComponent = "tSetGlobalVar_2";

				runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "row7");

				int tos_count_tSetGlobalVar_2 = 0;

				if (log.isDebugEnabled())
					log.debug("tSetGlobalVar_2 - " + ("Start to work."));
				if (log.isDebugEnabled()) {
					class BytesLimit65535_tSetGlobalVar_2 {
						public void limitLog4jByte() throws Exception {
							StringBuilder log4jParamters_tSetGlobalVar_2 = new StringBuilder();
							log4jParamters_tSetGlobalVar_2.append("Parameters:");
							log4jParamters_tSetGlobalVar_2.append("VARIABLES" + " = " + "[{VALUE=" + ("row7.groupId")
									+ ", KEY=" + ("\"groupId\"") + "}]");
							log4jParamters_tSetGlobalVar_2.append(" | ");
							if (log.isDebugEnabled())
								log.debug("tSetGlobalVar_2 - " + (log4jParamters_tSetGlobalVar_2));
						}
					}
					new BytesLimit65535_tSetGlobalVar_2().limitLog4jByte();
				}
				if (enableLogStash) {
					talendJobLog.addCM("tSetGlobalVar_2", "tSetGlobalVar");
					talendJobLogProcess(globalMap);
				}

				/**
				 * [tSetGlobalVar_2 begin ] stop
				 */

				/**
				 * [tLogRow_1 begin ] start
				 */

				ok_Hash.put("tLogRow_1", false);
				start_Hash.put("tLogRow_1", System.currentTimeMillis());

				currentComponent = "tLogRow_1";

				runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "row6");

				int tos_count_tLogRow_1 = 0;

				if (log.isDebugEnabled())
					log.debug("tLogRow_1 - " + ("Start to work."));
				if (log.isDebugEnabled()) {
					class BytesLimit65535_tLogRow_1 {
						public void limitLog4jByte() throws Exception {
							StringBuilder log4jParamters_tLogRow_1 = new StringBuilder();
							log4jParamters_tLogRow_1.append("Parameters:");
							log4jParamters_tLogRow_1.append("BASIC_MODE" + " = " + "true");
							log4jParamters_tLogRow_1.append(" | ");
							log4jParamters_tLogRow_1.append("TABLE_PRINT" + " = " + "false");
							log4jParamters_tLogRow_1.append(" | ");
							log4jParamters_tLogRow_1.append("VERTICAL" + " = " + "false");
							log4jParamters_tLogRow_1.append(" | ");
							log4jParamters_tLogRow_1.append("FIELDSEPARATOR" + " = " + "\",\"");
							log4jParamters_tLogRow_1.append(" | ");
							log4jParamters_tLogRow_1.append("PRINT_HEADER" + " = " + "false");
							log4jParamters_tLogRow_1.append(" | ");
							log4jParamters_tLogRow_1.append("PRINT_UNIQUE_NAME" + " = " + "false");
							log4jParamters_tLogRow_1.append(" | ");
							log4jParamters_tLogRow_1.append("PRINT_COLNAMES" + " = " + "false");
							log4jParamters_tLogRow_1.append(" | ");
							log4jParamters_tLogRow_1.append("USE_FIXED_LENGTH" + " = " + "false");
							log4jParamters_tLogRow_1.append(" | ");
							log4jParamters_tLogRow_1.append("PRINT_CONTENT_WITH_LOG4J" + " = " + "true");
							log4jParamters_tLogRow_1.append(" | ");
							if (log.isDebugEnabled())
								log.debug("tLogRow_1 - " + (log4jParamters_tLogRow_1));
						}
					}
					new BytesLimit65535_tLogRow_1().limitLog4jByte();
				}
				if (enableLogStash) {
					talendJobLog.addCM("tLogRow_1", "tLogRow");
					talendJobLogProcess(globalMap);
				}

				///////////////////////

				final String OUTPUT_FIELD_SEPARATOR_tLogRow_1 = ",";
				java.io.PrintStream consoleOut_tLogRow_1 = null;

				StringBuilder strBuffer_tLogRow_1 = null;
				int nb_line_tLogRow_1 = 0;
///////////////////////    			

				/**
				 * [tLogRow_1 begin ] stop
				 */

				/**
				 * [tExtractJSONFields_2 begin ] start
				 */

				ok_Hash.put("tExtractJSONFields_2", false);
				start_Hash.put("tExtractJSONFields_2", System.currentTimeMillis());

				currentComponent = "tExtractJSONFields_2";

				runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "row4");

				int tos_count_tExtractJSONFields_2 = 0;

				if (log.isDebugEnabled())
					log.debug("tExtractJSONFields_2 - " + ("Start to work."));
				if (log.isDebugEnabled()) {
					class BytesLimit65535_tExtractJSONFields_2 {
						public void limitLog4jByte() throws Exception {
							StringBuilder log4jParamters_tExtractJSONFields_2 = new StringBuilder();
							log4jParamters_tExtractJSONFields_2.append("Parameters:");
							log4jParamters_tExtractJSONFields_2.append("READ_BY" + " = " + "JSONPATH");
							log4jParamters_tExtractJSONFields_2.append(" | ");
							log4jParamters_tExtractJSONFields_2.append("JSON_PATH_VERSION" + " = " + "2_1_0");
							log4jParamters_tExtractJSONFields_2.append(" | ");
							log4jParamters_tExtractJSONFields_2.append("JSONFIELD" + " = " + "string");
							log4jParamters_tExtractJSONFields_2.append(" | ");
							log4jParamters_tExtractJSONFields_2.append("JSON_LOOP_QUERY" + " = " + "\"$\"");
							log4jParamters_tExtractJSONFields_2.append(" | ");
							log4jParamters_tExtractJSONFields_2.append("MAPPING_4_JSONPATH" + " = " + "[{QUERY="
									+ ("\"$.groupIds[0]\"") + ", SCHEMA_COLUMN=" + ("groupId") + "}]");
							log4jParamters_tExtractJSONFields_2.append(" | ");
							log4jParamters_tExtractJSONFields_2.append("DIE_ON_ERROR" + " = " + "false");
							log4jParamters_tExtractJSONFields_2.append(" | ");
							log4jParamters_tExtractJSONFields_2.append("USE_LOOP_AS_ROOT" + " = " + "true");
							log4jParamters_tExtractJSONFields_2.append(" | ");
							if (log.isDebugEnabled())
								log.debug("tExtractJSONFields_2 - " + (log4jParamters_tExtractJSONFields_2));
						}
					}
					new BytesLimit65535_tExtractJSONFields_2().limitLog4jByte();
				}
				if (enableLogStash) {
					talendJobLog.addCM("tExtractJSONFields_2", "tExtractJSONFields");
					talendJobLogProcess(globalMap);
				}

				int nb_line_tExtractJSONFields_2 = 0;
				String jsonStr_tExtractJSONFields_2 = "";

				class JsonPathCache_tExtractJSONFields_2 {
					final java.util.Map<String, com.jayway.jsonpath.JsonPath> jsonPathString2compiledJsonPath = new java.util.HashMap<String, com.jayway.jsonpath.JsonPath>();

					public com.jayway.jsonpath.JsonPath getCompiledJsonPath(String jsonPath) {
						if (jsonPathString2compiledJsonPath.containsKey(jsonPath)) {
							return jsonPathString2compiledJsonPath.get(jsonPath);
						} else {
							com.jayway.jsonpath.JsonPath compiledLoopPath = com.jayway.jsonpath.JsonPath
									.compile(jsonPath);
							jsonPathString2compiledJsonPath.put(jsonPath, compiledLoopPath);
							return compiledLoopPath;
						}
					}
				}

				JsonPathCache_tExtractJSONFields_2 jsonPathCache_tExtractJSONFields_2 = new JsonPathCache_tExtractJSONFields_2();

				/**
				 * [tExtractJSONFields_2 begin ] stop
				 */

				/**
				 * [tRESTClient_2 begin ] start
				 */

				ok_Hash.put("tRESTClient_2", false);
				start_Hash.put("tRESTClient_2", System.currentTimeMillis());

				currentComponent = "tRESTClient_2";

				int tos_count_tRESTClient_2 = 0;

				if (enableLogStash) {
					talendJobLog.addCM("tRESTClient_2", "tRESTClient");
					talendJobLogProcess(globalMap);
				}

				/**
				 * [tRESTClient_2 begin ] stop
				 */

				/**
				 * [tRESTClient_2 main ] start
				 */

				currentComponent = "tRESTClient_2";

				row4 = null;

// expected response body
				Object responseDoc_tRESTClient_2 = null;

				try {
					// request body
					org.dom4j.Document requestDoc_tRESTClient_2 = null;
					String requestString_tRESTClient_2 = null;

					Object requestBody_tRESTClient_2 = requestDoc_tRESTClient_2 != null ? requestDoc_tRESTClient_2
							: requestString_tRESTClient_2;

					// resposne class name
					Class<?> responseClass_tRESTClient_2 = String.class;

					// create web client instance
					org.apache.cxf.jaxrs.client.JAXRSClientFactoryBean factoryBean_tRESTClient_2 = new org.apache.cxf.jaxrs.client.JAXRSClientFactoryBean();

					boolean inOSGi = routines.system.BundleUtils.inOSGi();

					final java.util.List<org.apache.cxf.feature.Feature> features_tRESTClient_2 = new java.util.ArrayList<org.apache.cxf.feature.Feature>();

					String url = "https://api.ap.cloud.talend.com/tmc/v2.3/projects/" + globalMap.get("varProjectId")
							+ "/groups";
					// {baseUri}tRESTClient
					factoryBean_tRESTClient_2.setServiceName(new javax.xml.namespace.QName(url, "tRESTClient"));
					factoryBean_tRESTClient_2.setAddress(url);

					factoryBean_tRESTClient_2.setFeatures(features_tRESTClient_2);

					java.util.List<Object> providers_tRESTClient_2 = new java.util.ArrayList<Object>();
					providers_tRESTClient_2.add(new org.apache.cxf.jaxrs.provider.dom4j.DOM4JProvider() {
						// workaround for https://jira.talendforge.org/browse/TESB-7276
						public org.dom4j.Document readFrom(Class<org.dom4j.Document> cls, java.lang.reflect.Type type,
								java.lang.annotation.Annotation[] anns, javax.ws.rs.core.MediaType mt,
								javax.ws.rs.core.MultivaluedMap<String, String> headers, java.io.InputStream is)
								throws IOException, javax.ws.rs.WebApplicationException {
							String contentLength = headers.getFirst("Content-Length");
							if (!org.apache.cxf.common.util.StringUtils.isEmpty(contentLength)
									&& Integer.valueOf(contentLength) <= 0) {
								try {
									return org.dom4j.DocumentHelper.parseText("<root/>");
								} catch (org.dom4j.DocumentException e_tRESTClient_2) {
									e_tRESTClient_2.printStackTrace();
								}
								return null;
							}
							return super.readFrom(cls, type, anns, mt, headers, is);
						}
					});
					org.apache.cxf.jaxrs.provider.json.JSONProvider jsonProvider_tRESTClient_2 = new org.apache.cxf.jaxrs.provider.json.JSONProvider();
					jsonProvider_tRESTClient_2.setIgnoreNamespaces(true);
					jsonProvider_tRESTClient_2.setAttributesToElements(true);

					jsonProvider_tRESTClient_2.setSupportUnwrapped(true);
					jsonProvider_tRESTClient_2.setWrapperName("root");

					jsonProvider_tRESTClient_2.setDropRootElement(false);
					jsonProvider_tRESTClient_2.setConvertTypesToStrings(false);
					providers_tRESTClient_2.add(jsonProvider_tRESTClient_2);
					factoryBean_tRESTClient_2.setProviders(providers_tRESTClient_2);
					factoryBean_tRESTClient_2.setTransportId("http://cxf.apache.org/transports/http");

					boolean use_auth_tRESTClient_2 = true;

					if (use_auth_tRESTClient_2 && "SAML".equals("OAUTH2_BEARER")) {
						if (!inOSGi) {
							throw new IllegalArgumentException(
									"SAML based security scenarios are not supported in Studio (standalone)");
						}
						// set SAML Token authentication

						final String decryptedPassword_tRESTClient_2 = routines.system.PasswordEncryptUtil
								.decryptPassword(
										"enc:routine.encryption.key.v1:wdauMvj6T7+M+XD2xsnkgD9TLHFwTyjyOkca2iWSnfXeg9CHPA==");

						org.apache.cxf.ws.security.trust.STSClient stsClient = org.talend.esb.security.saml.STSClientUtils
								.createSTSClient(factoryBean_tRESTClient_2.getBus(), "tanakatanaka2@digdig.org",
										decryptedPassword_tRESTClient_2);

						org.talend.esb.security.saml.SAMLRESTUtils.configureClient(factoryBean_tRESTClient_2,
								stsClient);
					}

					org.apache.cxf.jaxrs.client.WebClient webClient_tRESTClient_2 = factoryBean_tRESTClient_2
							.createWebClient();

					// set request path
					webClient_tRESTClient_2.path("");

					// set connection properties
					org.apache.cxf.jaxrs.client.ClientConfiguration clientConfig_tRESTClient_2 = org.apache.cxf.jaxrs.client.WebClient
							.getConfig(webClient_tRESTClient_2);
					org.apache.cxf.transport.http.HTTPConduit conduit_tRESTClient_2 = clientConfig_tRESTClient_2
							.getHttpConduit();

					if (clientConfig_tRESTClient_2.getEndpoint() != null
							&& clientConfig_tRESTClient_2.getEndpoint().getEndpointInfo() != null) {
						clientConfig_tRESTClient_2.getEndpoint().getEndpointInfo()
								.setProperty("enable.webclient.operation.reporting", true);
					}

					if (use_auth_tRESTClient_2 && "BASIC".equals("OAUTH2_BEARER")) {
						// set BASIC auth

						final String decryptedPassword_tRESTClient_2 = routines.system.PasswordEncryptUtil
								.decryptPassword(
										"enc:routine.encryption.key.v1:eeDxCBipvzs5Km+7blnv3RvpuoZHYLQUyoNa2oVXM2R8Z11kVg==");

						org.apache.cxf.configuration.security.AuthorizationPolicy authPolicy_tRESTClient_2 = new org.apache.cxf.configuration.security.AuthorizationPolicy();
						authPolicy_tRESTClient_2.setAuthorizationType("Basic");
						authPolicy_tRESTClient_2.setUserName("tanakatanaka2@digdig.org");
						authPolicy_tRESTClient_2.setPassword(decryptedPassword_tRESTClient_2);
						conduit_tRESTClient_2.setAuthorization(authPolicy_tRESTClient_2);
					} else if (use_auth_tRESTClient_2 && "HTTP Digest".equals("OAUTH2_BEARER")) {
						// set Digest auth

						final String decryptedPassword_tRESTClient_2 = routines.system.PasswordEncryptUtil
								.decryptPassword(
										"enc:routine.encryption.key.v1:EVaDaEMOSWqESK7Tz0m0gFztN1xg5O/dMf0HpePcmsLh8vdVsg==");

						org.apache.cxf.configuration.security.AuthorizationPolicy authPolicy_tRESTClient_2 = new org.apache.cxf.configuration.security.AuthorizationPolicy();
						authPolicy_tRESTClient_2.setAuthorizationType("Digest");
						authPolicy_tRESTClient_2.setUserName("tanakatanaka2@digdig.org");
						authPolicy_tRESTClient_2.setPassword(decryptedPassword_tRESTClient_2);
						conduit_tRESTClient_2.setAuthorization(authPolicy_tRESTClient_2);
					}

					if (!inOSGi) {
						conduit_tRESTClient_2.getClient().setReceiveTimeout((long) (60 * 1000L));
						conduit_tRESTClient_2.getClient().setConnectionTimeout((long) (30 * 1000L));
						boolean use_proxy_tRESTClient_2 = false;

					}

					// set Accept-Type
					webClient_tRESTClient_2.accept("application/json");

					// set optional query and header properties if any

					if (use_auth_tRESTClient_2 && "OAUTH2_BEARER".equals("OAUTH2_BEARER")) {
						// set oAuth2 bearer token
						webClient_tRESTClient_2.header("Authorization",
								"Bearer " + "N1Hi0zFKRxie79flQEBmNubF8okwmsQgP1Eyhb93Slz8MKgtRVKQQXXdN0_PZQRP");
					}

					if (use_auth_tRESTClient_2 && "OIDC_PASSWORD_GRANT".equals("OAUTH2_BEARER")) {

						final String decryptedPassword_tRESTClient_2 = routines.system.PasswordEncryptUtil
								.decryptPassword(
										"enc:routine.encryption.key.v1:tOH7JMatl6+BymmTbaKpqR5ehBlwntdbkaVQE3wHycIJQ5I/VA==");

						String username_tRESTClient_2 = "tanakatanaka2@digdig.org";
						String password_tRESTClient_2 = decryptedPassword_tRESTClient_2;

						String bearerHeader_tRESTClient_2 = null;

						try {
							if (!inOSGi) {
								org.springframework.context.support.ClassPathXmlApplicationContext context_tRESTClient_2 = new org.springframework.context.support.ClassPathXmlApplicationContext(
										"META-INF/tesb/oidc-context.xml");
								org.talend.esb.security.oidc.OidcConfiguration oidcConfiguration_tRESTClient_2 = context_tRESTClient_2
										.getBean(org.talend.esb.security.oidc.OidcConfiguration.class);
								bearerHeader_tRESTClient_2 = org.talend.esb.security.oidc.OidcClientUtils
										.oidcClientBearer(username_tRESTClient_2, password_tRESTClient_2,
												oidcConfiguration_tRESTClient_2);
							} else {
								bearerHeader_tRESTClient_2 = org.talend.esb.security.oidc.OidcClientUtils
										.oidcClientBearer(username_tRESTClient_2, password_tRESTClient_2);
							}
						} catch (Exception ex) {
							throw new javax.ws.rs.WebApplicationException("Failed to get OIDC access token: ", ex);
						}

						webClient_tRESTClient_2.header("Authorization", bearerHeader_tRESTClient_2);
					}

					// if FORM request then capture query parameters into Form, otherwise set them
					// as queries

					try {
						// start send request

						responseDoc_tRESTClient_2 = webClient_tRESTClient_2.get(responseClass_tRESTClient_2);

						int webClientResponseStatus_tRESTClient_2 = webClient_tRESTClient_2.getResponse().getStatus();
						if (webClientResponseStatus_tRESTClient_2 >= 300) {
							throw new javax.ws.rs.WebApplicationException(webClient_tRESTClient_2.getResponse());
						}

						if (row4 == null) {
							row4 = new row4Struct();
						}

						row4.statusCode = webClientResponseStatus_tRESTClient_2;

						{
							Object responseObj_tRESTClient_2 = responseDoc_tRESTClient_2;

							if (responseObj_tRESTClient_2 != null) {
								if (responseClass_tRESTClient_2 == String.class) {
									row4.string = (String) responseObj_tRESTClient_2;
								} else {
									routines.system.Document responseTalendDoc_tRESTClient_2 = null;
									if (null != responseObj_tRESTClient_2) {
										responseTalendDoc_tRESTClient_2 = new routines.system.Document();
										responseTalendDoc_tRESTClient_2
												.setDocument((org.dom4j.Document) responseObj_tRESTClient_2);
									}
									row4.body = responseTalendDoc_tRESTClient_2;
								}
							}
						}

						globalMap.put("tRESTClient_2_HEADERS", webClient_tRESTClient_2.getResponse().getHeaders());

					} catch (javax.ws.rs.WebApplicationException ex_tRESTClient_2) {

						throw ex_tRESTClient_2;

					}

				} catch (Exception e_tRESTClient_2) {

					throw new TalendException(e_tRESTClient_2, currentComponent, globalMap);

				}

				tos_count_tRESTClient_2++;

				/**
				 * [tRESTClient_2 main ] stop
				 */

				/**
				 * [tRESTClient_2 process_data_begin ] start
				 */

				currentComponent = "tRESTClient_2";

				/**
				 * [tRESTClient_2 process_data_begin ] stop
				 */
// Start of branch "row4"
				if (row4 != null) {

					/**
					 * [tExtractJSONFields_2 main ] start
					 */

					currentComponent = "tExtractJSONFields_2";

					runStat.updateStatAndLog(execStat, enableLogStash, iterateId, 1, 1, "row4");

					if (log.isTraceEnabled()) {
						log.trace("row4 - " + (row4 == null ? "" : row4.toLogString()));
					}

					if (row4.string != null) {// C_01
						jsonStr_tExtractJSONFields_2 = row4.string.toString();

						row6 = null;

						String loopPath_tExtractJSONFields_2 = "$";
						java.util.List<Object> resultset_tExtractJSONFields_2 = new java.util.ArrayList<Object>();

						boolean isStructError_tExtractJSONFields_2 = true;
						com.jayway.jsonpath.ReadContext document_tExtractJSONFields_2 = null;
						try {
							document_tExtractJSONFields_2 = com.jayway.jsonpath.JsonPath
									.parse(jsonStr_tExtractJSONFields_2);
							com.jayway.jsonpath.JsonPath compiledLoopPath_tExtractJSONFields_2 = jsonPathCache_tExtractJSONFields_2
									.getCompiledJsonPath(loopPath_tExtractJSONFields_2);
							Object result_tExtractJSONFields_2 = document_tExtractJSONFields_2
									.read(compiledLoopPath_tExtractJSONFields_2, net.minidev.json.JSONObject.class);
							if (result_tExtractJSONFields_2 instanceof net.minidev.json.JSONArray) {
								resultset_tExtractJSONFields_2 = (net.minidev.json.JSONArray) result_tExtractJSONFields_2;
							} else {
								resultset_tExtractJSONFields_2.add(result_tExtractJSONFields_2);
							}

							isStructError_tExtractJSONFields_2 = false;
						} catch (java.lang.Exception ex_tExtractJSONFields_2) {
							log.error("tExtractJSONFields_2 - " + ex_tExtractJSONFields_2.getMessage());
							System.err.println(ex_tExtractJSONFields_2.getMessage());
						}

						String jsonPath_tExtractJSONFields_2 = null;
						com.jayway.jsonpath.JsonPath compiledJsonPath_tExtractJSONFields_2 = null;

						Object value_tExtractJSONFields_2 = null;

						Object root_tExtractJSONFields_2 = null;
						for (int i_tExtractJSONFields_2 = 0; isStructError_tExtractJSONFields_2
								|| (i_tExtractJSONFields_2 < resultset_tExtractJSONFields_2
										.size()); i_tExtractJSONFields_2++) {
							if (!isStructError_tExtractJSONFields_2) {
								Object row_tExtractJSONFields_2 = resultset_tExtractJSONFields_2
										.get(i_tExtractJSONFields_2);
								row6 = null;
								row6 = new row6Struct();
								nb_line_tExtractJSONFields_2++;
								try {
									jsonPath_tExtractJSONFields_2 = "$.groupIds[0]";
									compiledJsonPath_tExtractJSONFields_2 = jsonPathCache_tExtractJSONFields_2
											.getCompiledJsonPath(jsonPath_tExtractJSONFields_2);

									try {

										value_tExtractJSONFields_2 = compiledJsonPath_tExtractJSONFields_2
												.read(row_tExtractJSONFields_2);

										row6.groupId = value_tExtractJSONFields_2 == null ?

												null

												: value_tExtractJSONFields_2.toString();
									} catch (com.jayway.jsonpath.PathNotFoundException e_tExtractJSONFields_2) {
										row6.groupId =

												null

										;
									}
								} catch (java.lang.Exception ex_tExtractJSONFields_2) {
									log.error("tExtractJSONFields_2 - " + ex_tExtractJSONFields_2.getMessage());
									System.err.println(ex_tExtractJSONFields_2.getMessage());
									row6 = null;
								}

							}

							isStructError_tExtractJSONFields_2 = false;

							log.debug("tExtractJSONFields_2 - Extracting the record " + nb_line_tExtractJSONFields_2
									+ ".");
//}

							tos_count_tExtractJSONFields_2++;

							/**
							 * [tExtractJSONFields_2 main ] stop
							 */

							/**
							 * [tExtractJSONFields_2 process_data_begin ] start
							 */

							currentComponent = "tExtractJSONFields_2";

							/**
							 * [tExtractJSONFields_2 process_data_begin ] stop
							 */
// Start of branch "row6"
							if (row6 != null) {

								/**
								 * [tLogRow_1 main ] start
								 */

								currentComponent = "tLogRow_1";

								runStat.updateStatAndLog(execStat, enableLogStash, iterateId, 1, 1, "row6");

								if (log.isTraceEnabled()) {
									log.trace("row6 - " + (row6 == null ? "" : row6.toLogString()));
								}

///////////////////////		

								strBuffer_tLogRow_1 = new StringBuilder();

								if (row6.groupId != null) { //

									strBuffer_tLogRow_1.append(String.valueOf(row6.groupId));

								} //

								if (globalMap.get("tLogRow_CONSOLE") != null) {
									consoleOut_tLogRow_1 = (java.io.PrintStream) globalMap.get("tLogRow_CONSOLE");
								} else {
									consoleOut_tLogRow_1 = new java.io.PrintStream(
											new java.io.BufferedOutputStream(System.out));
									globalMap.put("tLogRow_CONSOLE", consoleOut_tLogRow_1);
								}
								log.info("tLogRow_1 - Content of row " + (nb_line_tLogRow_1 + 1) + ": "
										+ strBuffer_tLogRow_1.toString());
								consoleOut_tLogRow_1.println(strBuffer_tLogRow_1.toString());
								consoleOut_tLogRow_1.flush();
								nb_line_tLogRow_1++;
//////

//////                    

///////////////////////    			

								row7 = row6;

								tos_count_tLogRow_1++;

								/**
								 * [tLogRow_1 main ] stop
								 */

								/**
								 * [tLogRow_1 process_data_begin ] start
								 */

								currentComponent = "tLogRow_1";

								/**
								 * [tLogRow_1 process_data_begin ] stop
								 */

								/**
								 * [tSetGlobalVar_2 main ] start
								 */

								currentComponent = "tSetGlobalVar_2";

								runStat.updateStatAndLog(execStat, enableLogStash, iterateId, 1, 1, "row7");

								if (log.isTraceEnabled()) {
									log.trace("row7 - " + (row7 == null ? "" : row7.toLogString()));
								}

								globalMap.put("groupId", row7.groupId);

								row10 = row7;

								tos_count_tSetGlobalVar_2++;

								/**
								 * [tSetGlobalVar_2 main ] stop
								 */

								/**
								 * [tSetGlobalVar_2 process_data_begin ] start
								 */

								currentComponent = "tSetGlobalVar_2";

								/**
								 * [tSetGlobalVar_2 process_data_begin ] stop
								 */

								/**
								 * [tMap_1 main ] start
								 */

								currentComponent = "tMap_1";

								runStat.updateStatAndLog(execStat, enableLogStash, iterateId, 1, 1, "row10");

								if (log.isTraceEnabled()) {
									log.trace("row10 - " + (row10 == null ? "" : row10.toLogString()));
								}

								boolean hasCasePrimitiveKeyWithNull_tMap_1 = false;

								// ###############################
								// # Input tables (lookups)
								boolean rejectedInnerJoin_tMap_1 = false;
								boolean mainRowRejected_tMap_1 = false;

								// ###############################
								{ // start of Var scope

									// ###############################
									// # Vars tables

									Var__tMap_1__Struct Var = Var__tMap_1;// ###############################
									// ###############################
									// # Output tables

									map1 = null;

// # Output table : 'map1'
									count_map1_tMap_1++;

									map1_tmp.body = null;
									map1_tmp.string = "{users: [], groups: [{level: {code: \"READER\"}, groupId: \" + row10.groupId + \"}]}";
									map1 = map1_tmp;
									log.debug("tMap_1 - Outputting the record " + count_map1_tMap_1
											+ " of the output table 'map1'.");

// ###############################

								} // end of Var scope

								rejectedInnerJoin_tMap_1 = false;

								tos_count_tMap_1++;

								/**
								 * [tMap_1 main ] stop
								 */

								/**
								 * [tMap_1 process_data_begin ] start
								 */

								currentComponent = "tMap_1";

								/**
								 * [tMap_1 process_data_begin ] stop
								 */
// Start of branch "map1"
								if (map1 != null) {

									/**
									 * [tRESTClient_4 main ] start
									 */

									currentComponent = "tRESTClient_4";

									runStat.updateStatAndLog(execStat, enableLogStash, iterateId, 1, 1, "map1");

									if (log.isTraceEnabled()) {
										log.trace("map1 - " + (map1 == null ? "" : map1.toLogString()));
									}

// expected response body
									javax.ws.rs.core.Response responseDoc_tRESTClient_4 = null;

									try {
										// request body
										org.dom4j.Document requestDoc_tRESTClient_4 = null;
										String requestString_tRESTClient_4 = null;
										if (null != map1.body) {
											requestDoc_tRESTClient_4 = map1.body.getDocument();
										}
										requestString_tRESTClient_4 = map1.string;

										Object requestBody_tRESTClient_4 = requestDoc_tRESTClient_4 != null
												? requestDoc_tRESTClient_4
												: requestString_tRESTClient_4;

										// resposne class name
										Class<?> responseClass_tRESTClient_4 = String.class;

										// create web client instance
										org.apache.cxf.jaxrs.client.JAXRSClientFactoryBean factoryBean_tRESTClient_4 = new org.apache.cxf.jaxrs.client.JAXRSClientFactoryBean();

										boolean inOSGi = routines.system.BundleUtils.inOSGi();

										final java.util.List<org.apache.cxf.feature.Feature> features_tRESTClient_4 = new java.util.ArrayList<org.apache.cxf.feature.Feature>();

										String url = "https://tdp.ap.cloud.talend.com/sharing/v1/sharingset/dataset/"
												+ globalMap.get("varDatasetId");
										// {baseUri}tRESTClient
										factoryBean_tRESTClient_4
												.setServiceName(new javax.xml.namespace.QName(url, "tRESTClient"));
										factoryBean_tRESTClient_4.setAddress(url);

										factoryBean_tRESTClient_4.setFeatures(features_tRESTClient_4);

										java.util.List<Object> providers_tRESTClient_4 = new java.util.ArrayList<Object>();
										providers_tRESTClient_4
												.add(new org.apache.cxf.jaxrs.provider.dom4j.DOM4JProvider() {
													// workaround for https://jira.talendforge.org/browse/TESB-7276
													public org.dom4j.Document readFrom(Class<org.dom4j.Document> cls,
															java.lang.reflect.Type type,
															java.lang.annotation.Annotation[] anns,
															javax.ws.rs.core.MediaType mt,
															javax.ws.rs.core.MultivaluedMap<String, String> headers,
															java.io.InputStream is)
															throws IOException, javax.ws.rs.WebApplicationException {
														String contentLength = headers.getFirst("Content-Length");
														if (!org.apache.cxf.common.util.StringUtils
																.isEmpty(contentLength)
																&& Integer.valueOf(contentLength) <= 0) {
															try {
																return org.dom4j.DocumentHelper.parseText("<root/>");
															} catch (org.dom4j.DocumentException e_tRESTClient_4) {
																e_tRESTClient_4.printStackTrace();
															}
															return null;
														}
														return super.readFrom(cls, type, anns, mt, headers, is);
													}
												});
										org.apache.cxf.jaxrs.provider.json.JSONProvider jsonProvider_tRESTClient_4 = new org.apache.cxf.jaxrs.provider.json.JSONProvider();
										jsonProvider_tRESTClient_4.setIgnoreNamespaces(true);
										jsonProvider_tRESTClient_4.setAttributesToElements(true);

										jsonProvider_tRESTClient_4.setSupportUnwrapped(true);
										jsonProvider_tRESTClient_4.setWrapperName("root");

										jsonProvider_tRESTClient_4.setDropRootElement(false);
										jsonProvider_tRESTClient_4.setConvertTypesToStrings(false);
										providers_tRESTClient_4.add(jsonProvider_tRESTClient_4);
										factoryBean_tRESTClient_4.setProviders(providers_tRESTClient_4);
										factoryBean_tRESTClient_4
												.setTransportId("http://cxf.apache.org/transports/http");

										boolean use_auth_tRESTClient_4 = true;

										if (use_auth_tRESTClient_4 && "SAML".equals("OAUTH2_BEARER")) {
											if (!inOSGi) {
												throw new IllegalArgumentException(
														"SAML based security scenarios are not supported in Studio (standalone)");
											}
											// set SAML Token authentication

											final String decryptedPassword_tRESTClient_4 = routines.system.PasswordEncryptUtil
													.decryptPassword(
															"enc:routine.encryption.key.v1:l3NmC4UZh+G86lS3kMAcJKgq5yH477QehXIdb4gmWi5Xc3Vw");

											org.apache.cxf.ws.security.trust.STSClient stsClient = org.talend.esb.security.saml.STSClientUtils
													.createSTSClient(factoryBean_tRESTClient_4.getBus(), "username",
															decryptedPassword_tRESTClient_4);

											org.talend.esb.security.saml.SAMLRESTUtils
													.configureClient(factoryBean_tRESTClient_4, stsClient);
										}

										org.apache.cxf.jaxrs.client.WebClient webClient_tRESTClient_4 = factoryBean_tRESTClient_4
												.createWebClient();

										// set request path
										webClient_tRESTClient_4.path("");

										// set connection properties
										org.apache.cxf.jaxrs.client.ClientConfiguration clientConfig_tRESTClient_4 = org.apache.cxf.jaxrs.client.WebClient
												.getConfig(webClient_tRESTClient_4);
										org.apache.cxf.transport.http.HTTPConduit conduit_tRESTClient_4 = clientConfig_tRESTClient_4
												.getHttpConduit();

										if (clientConfig_tRESTClient_4.getEndpoint() != null
												&& clientConfig_tRESTClient_4.getEndpoint().getEndpointInfo() != null) {
											clientConfig_tRESTClient_4.getEndpoint().getEndpointInfo()
													.setProperty("enable.webclient.operation.reporting", true);
										}

										if (use_auth_tRESTClient_4 && "BASIC".equals("OAUTH2_BEARER")) {
											// set BASIC auth

											final String decryptedPassword_tRESTClient_4 = routines.system.PasswordEncryptUtil
													.decryptPassword(
															"enc:routine.encryption.key.v1:urumD33JlA0Pr7ICvePVwvoBPGdu6CYvg7PTVCAT6xDDEod6");

											org.apache.cxf.configuration.security.AuthorizationPolicy authPolicy_tRESTClient_4 = new org.apache.cxf.configuration.security.AuthorizationPolicy();
											authPolicy_tRESTClient_4.setAuthorizationType("Basic");
											authPolicy_tRESTClient_4.setUserName("username");
											authPolicy_tRESTClient_4.setPassword(decryptedPassword_tRESTClient_4);
											conduit_tRESTClient_4.setAuthorization(authPolicy_tRESTClient_4);
										} else if (use_auth_tRESTClient_4 && "HTTP Digest".equals("OAUTH2_BEARER")) {
											// set Digest auth

											final String decryptedPassword_tRESTClient_4 = routines.system.PasswordEncryptUtil
													.decryptPassword(
															"enc:routine.encryption.key.v1:aIZ0XXEQJOhXWTtFlgJ64oNNkvtvcFtOtrDiclhLN6Np8uOH");

											org.apache.cxf.configuration.security.AuthorizationPolicy authPolicy_tRESTClient_4 = new org.apache.cxf.configuration.security.AuthorizationPolicy();
											authPolicy_tRESTClient_4.setAuthorizationType("Digest");
											authPolicy_tRESTClient_4.setUserName("username");
											authPolicy_tRESTClient_4.setPassword(decryptedPassword_tRESTClient_4);
											conduit_tRESTClient_4.setAuthorization(authPolicy_tRESTClient_4);
										}

										if (!inOSGi) {
											conduit_tRESTClient_4.getClient().setReceiveTimeout((long) (60 * 1000L));
											conduit_tRESTClient_4.getClient().setConnectionTimeout((long) (30 * 1000L));
											boolean use_proxy_tRESTClient_4 = false;

										}

										// set Content-Type
										webClient_tRESTClient_4.type("application/json");

										// set Accept-Type
										webClient_tRESTClient_4.accept("application/json");

										// set optional query and header properties if any

										if (use_auth_tRESTClient_4 && "OAUTH2_BEARER".equals("OAUTH2_BEARER")) {
											// set oAuth2 bearer token
											webClient_tRESTClient_4.header("Authorization", "Bearer "
													+ "N1Hi0zFKRxie79flQEBmNubF8okwmsQgP1Eyhb93Slz8MKgtRVKQQXXdN0_PZQRP");
										}

										if (use_auth_tRESTClient_4 && "OIDC_PASSWORD_GRANT".equals("OAUTH2_BEARER")) {

											final String decryptedPassword_tRESTClient_4 = routines.system.PasswordEncryptUtil
													.decryptPassword(
															"enc:routine.encryption.key.v1:z+wpfpDdcotEQN/WP06+8dShcrPacnbJLG1GyOplNJ3uhlCO");

											String username_tRESTClient_4 = "username";
											String password_tRESTClient_4 = decryptedPassword_tRESTClient_4;

											String bearerHeader_tRESTClient_4 = null;

											try {
												if (!inOSGi) {
													org.springframework.context.support.ClassPathXmlApplicationContext context_tRESTClient_4 = new org.springframework.context.support.ClassPathXmlApplicationContext(
															"META-INF/tesb/oidc-context.xml");
													org.talend.esb.security.oidc.OidcConfiguration oidcConfiguration_tRESTClient_4 = context_tRESTClient_4
															.getBean(
																	org.talend.esb.security.oidc.OidcConfiguration.class);
													bearerHeader_tRESTClient_4 = org.talend.esb.security.oidc.OidcClientUtils
															.oidcClientBearer(username_tRESTClient_4,
																	password_tRESTClient_4,
																	oidcConfiguration_tRESTClient_4);
												} else {
													bearerHeader_tRESTClient_4 = org.talend.esb.security.oidc.OidcClientUtils
															.oidcClientBearer(username_tRESTClient_4,
																	password_tRESTClient_4);
												}
											} catch (Exception ex) {
												throw new javax.ws.rs.WebApplicationException(
														"Failed to get OIDC access token: ", ex);
											}

											webClient_tRESTClient_4.header("Authorization", bearerHeader_tRESTClient_4);
										}

										// if FORM request then capture query parameters into Form, otherwise set them
										// as queries

										try {
											// start send request

											org.apache.cxf.jaxrs.client.WebClient.getConfig(webClient_tRESTClient_4)
													.getRequestContext()
													.put("use.httpurlconnection.method.reflection", true);
											responseDoc_tRESTClient_4 = webClient_tRESTClient_4.invoke("PATCH",
													requestBody_tRESTClient_4);

											int webClientResponseStatus_tRESTClient_4 = webClient_tRESTClient_4
													.getResponse().getStatus();
											if (webClientResponseStatus_tRESTClient_4 >= 300) {
												throw new javax.ws.rs.WebApplicationException(
														webClient_tRESTClient_4.getResponse());
											}

										} catch (javax.ws.rs.WebApplicationException ex_tRESTClient_4) {

											throw ex_tRESTClient_4;

										}

									} catch (Exception e_tRESTClient_4) {

										throw new TalendException(e_tRESTClient_4, currentComponent, globalMap);

									}

									tos_count_tRESTClient_4++;

									/**
									 * [tRESTClient_4 main ] stop
									 */

									/**
									 * [tRESTClient_4 process_data_begin ] start
									 */

									currentComponent = "tRESTClient_4";

									/**
									 * [tRESTClient_4 process_data_begin ] stop
									 */

									/**
									 * [tRESTClient_4 process_data_end ] start
									 */

									currentComponent = "tRESTClient_4";

									/**
									 * [tRESTClient_4 process_data_end ] stop
									 */

								} // End of branch "map1"

								/**
								 * [tMap_1 process_data_end ] start
								 */

								currentComponent = "tMap_1";

								/**
								 * [tMap_1 process_data_end ] stop
								 */

								/**
								 * [tSetGlobalVar_2 process_data_end ] start
								 */

								currentComponent = "tSetGlobalVar_2";

								/**
								 * [tSetGlobalVar_2 process_data_end ] stop
								 */

								/**
								 * [tLogRow_1 process_data_end ] start
								 */

								currentComponent = "tLogRow_1";

								/**
								 * [tLogRow_1 process_data_end ] stop
								 */

							} // End of branch "row6"

							// end for
						}

					} // C_01

					/**
					 * [tExtractJSONFields_2 process_data_end ] start
					 */

					currentComponent = "tExtractJSONFields_2";

					/**
					 * [tExtractJSONFields_2 process_data_end ] stop
					 */

				} // End of branch "row4"

				/**
				 * [tRESTClient_2 process_data_end ] start
				 */

				currentComponent = "tRESTClient_2";

				/**
				 * [tRESTClient_2 process_data_end ] stop
				 */

				/**
				 * [tRESTClient_2 end ] start
				 */

				currentComponent = "tRESTClient_2";

				if (globalMap.get("tRESTClient_2_NB_LINE") == null) {
					globalMap.put("tRESTClient_2_NB_LINE", 1);
				}

// [tRESTCliend_end]

				ok_Hash.put("tRESTClient_2", true);
				end_Hash.put("tRESTClient_2", System.currentTimeMillis());

				/**
				 * [tRESTClient_2 end ] stop
				 */

				/**
				 * [tExtractJSONFields_2 end ] start
				 */

				currentComponent = "tExtractJSONFields_2";

				globalMap.put("tExtractJSONFields_2_NB_LINE", nb_line_tExtractJSONFields_2);
				log.debug("tExtractJSONFields_2 - Extracted records count: " + nb_line_tExtractJSONFields_2 + " .");

				if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "row4", 2, 0,
						talendJobLog, "tRESTClient_2", "tRESTClient", "tExtractJSONFields_2", "tExtractJSONFields",
						"output")) {
					talendJobLogProcess(globalMap);
				}

				if (log.isDebugEnabled())
					log.debug("tExtractJSONFields_2 - " + ("Done."));

				ok_Hash.put("tExtractJSONFields_2", true);
				end_Hash.put("tExtractJSONFields_2", System.currentTimeMillis());

				/**
				 * [tExtractJSONFields_2 end ] stop
				 */

				/**
				 * [tLogRow_1 end ] start
				 */

				currentComponent = "tLogRow_1";

//////
//////
				globalMap.put("tLogRow_1_NB_LINE", nb_line_tLogRow_1);
				if (log.isInfoEnabled())
					log.info("tLogRow_1 - " + ("Printed row count: ") + (nb_line_tLogRow_1) + ("."));

///////////////////////    			

				if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "row6", 2, 0,
						talendJobLog, "tExtractJSONFields_2", "tExtractJSONFields", "tLogRow_1", "tLogRow", "output")) {
					talendJobLogProcess(globalMap);
				}

				if (log.isDebugEnabled())
					log.debug("tLogRow_1 - " + ("Done."));

				ok_Hash.put("tLogRow_1", true);
				end_Hash.put("tLogRow_1", System.currentTimeMillis());

				/**
				 * [tLogRow_1 end ] stop
				 */

				/**
				 * [tSetGlobalVar_2 end ] start
				 */

				currentComponent = "tSetGlobalVar_2";

				if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "row7", 2, 0,
						talendJobLog, "tLogRow_1", "tLogRow", "tSetGlobalVar_2", "tSetGlobalVar", "output")) {
					talendJobLogProcess(globalMap);
				}

				if (log.isDebugEnabled())
					log.debug("tSetGlobalVar_2 - " + ("Done."));

				ok_Hash.put("tSetGlobalVar_2", true);
				end_Hash.put("tSetGlobalVar_2", System.currentTimeMillis());

				/**
				 * [tSetGlobalVar_2 end ] stop
				 */

				/**
				 * [tMap_1 end ] start
				 */

				currentComponent = "tMap_1";

// ###############################
// # Lookup hashes releasing
// ###############################      
				log.debug("tMap_1 - Written records count in the table 'map1': " + count_map1_tMap_1 + ".");

				if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "row10", 2, 0,
						talendJobLog, "tSetGlobalVar_2", "tSetGlobalVar", "tMap_1", "tMap", "output")) {
					talendJobLogProcess(globalMap);
				}

				if (log.isDebugEnabled())
					log.debug("tMap_1 - " + ("Done."));

				ok_Hash.put("tMap_1", true);
				end_Hash.put("tMap_1", System.currentTimeMillis());

				/**
				 * [tMap_1 end ] stop
				 */

				/**
				 * [tRESTClient_4 end ] start
				 */

				currentComponent = "tRESTClient_4";

				if (globalMap.get("tRESTClient_4_NB_LINE") == null) {
					globalMap.put("tRESTClient_4_NB_LINE", 1);
				}

// [tRESTCliend_end]
				if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "map1", 2, 0,
						talendJobLog, "tMap_1", "tMap", "tRESTClient_4", "tRESTClient", "output")) {
					talendJobLogProcess(globalMap);
				}

				ok_Hash.put("tRESTClient_4", true);
				end_Hash.put("tRESTClient_4", System.currentTimeMillis());

				/**
				 * [tRESTClient_4 end ] stop
				 */

			} // end the resume

		} catch (java.lang.Exception e) {

			if (!(e instanceof TalendException)) {
				log.fatal(currentComponent + " " + e.getMessage(), e);
			}

			TalendException te = new TalendException(e, currentComponent, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			try {

				/**
				 * [tRESTClient_2 finally ] start
				 */

				currentComponent = "tRESTClient_2";

				/**
				 * [tRESTClient_2 finally ] stop
				 */

				/**
				 * [tExtractJSONFields_2 finally ] start
				 */

				currentComponent = "tExtractJSONFields_2";

				/**
				 * [tExtractJSONFields_2 finally ] stop
				 */

				/**
				 * [tLogRow_1 finally ] start
				 */

				currentComponent = "tLogRow_1";

				/**
				 * [tLogRow_1 finally ] stop
				 */

				/**
				 * [tSetGlobalVar_2 finally ] start
				 */

				currentComponent = "tSetGlobalVar_2";

				/**
				 * [tSetGlobalVar_2 finally ] stop
				 */

				/**
				 * [tMap_1 finally ] start
				 */

				currentComponent = "tMap_1";

				/**
				 * [tMap_1 finally ] stop
				 */

				/**
				 * [tRESTClient_4 finally ] start
				 */

				currentComponent = "tRESTClient_4";

				/**
				 * [tRESTClient_4 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tRESTClient_2_SUBPROCESS_STATE", 1);
	}

	public void talendJobLogProcess(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("talendJobLog_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		String iterateId = "";

		String currentComponent = "";
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				/**
				 * [talendJobLog begin ] start
				 */

				ok_Hash.put("talendJobLog", false);
				start_Hash.put("talendJobLog", System.currentTimeMillis());

				currentComponent = "talendJobLog";

				int tos_count_talendJobLog = 0;

				for (JobStructureCatcherUtils.JobStructureCatcherMessage jcm : talendJobLog.getMessages()) {
					org.talend.logging.audit.Context log_context_talendJobLog = null;
					if (jcm.component_name == null) {// job level log
						if (jcm.status == null) {// job start
							log_context_talendJobLog = org.talend.job.audit.JobContextBuilder.create()
									.jobName(jcm.job_name).jobId(jcm.job_id).jobVersion(jcm.job_version)
									.timestamp(jcm.moment).build();
							auditLogger_talendJobLog.jobstart(log_context_talendJobLog);
						} else {// job end
							long timeMS = jcm.end_time - jcm.start_time;
							String duration = String.format(java.util.Locale.US, "%1$.2fs", (timeMS * 1.0) / 1000);

							log_context_talendJobLog = org.talend.job.audit.JobContextBuilder.create()
									.jobName(jcm.job_name).jobId(jcm.job_id).jobVersion(jcm.job_version)
									.timestamp(jcm.moment).duration(duration).status(jcm.status).build();
							auditLogger_talendJobLog.jobstop(log_context_talendJobLog);
						}
					} else if (jcm.current_connector == null) {// component log
						log_context_talendJobLog = org.talend.job.audit.JobContextBuilder.create().jobName(jcm.job_name)
								.jobId(jcm.job_id).jobVersion(jcm.job_version).connectorType(jcm.component_name)
								.connectorId(jcm.component_id).build();
						auditLogger_talendJobLog.runcomponent(log_context_talendJobLog);
					} else {// component connector meter log
						long timeMS = jcm.end_time - jcm.start_time;
						String duration = String.format(java.util.Locale.US, "%1$.2fs", (timeMS * 1.0) / 1000);

						if (jcm.current_connector_as_input) {// log current component input line
							log_context_talendJobLog = org.talend.job.audit.JobContextBuilder.create()
									.jobName(jcm.job_name).jobId(jcm.job_id).jobVersion(jcm.job_version)
									.connectorType(jcm.component_name).connectorId(jcm.component_id)
									.connectionName(jcm.current_connector).connectionType(jcm.current_connector_type)
									.rows(jcm.total_row_number).duration(duration).build();
							auditLogger_talendJobLog.flowInput(log_context_talendJobLog);
						} else {// log current component output/reject line
							log_context_talendJobLog = org.talend.job.audit.JobContextBuilder.create()
									.jobName(jcm.job_name).jobId(jcm.job_id).jobVersion(jcm.job_version)
									.connectorType(jcm.component_name).connectorId(jcm.component_id)
									.connectionName(jcm.current_connector).connectionType(jcm.current_connector_type)
									.rows(jcm.total_row_number).duration(duration).build();
							auditLogger_talendJobLog.flowOutput(log_context_talendJobLog);
						}
					}
				}

				/**
				 * [talendJobLog begin ] stop
				 */

				/**
				 * [talendJobLog main ] start
				 */

				currentComponent = "talendJobLog";

				tos_count_talendJobLog++;

				/**
				 * [talendJobLog main ] stop
				 */

				/**
				 * [talendJobLog process_data_begin ] start
				 */

				currentComponent = "talendJobLog";

				/**
				 * [talendJobLog process_data_begin ] stop
				 */

				/**
				 * [talendJobLog process_data_end ] start
				 */

				currentComponent = "talendJobLog";

				/**
				 * [talendJobLog process_data_end ] stop
				 */

				/**
				 * [talendJobLog end ] start
				 */

				currentComponent = "talendJobLog";

				ok_Hash.put("talendJobLog", true);
				end_Hash.put("talendJobLog", System.currentTimeMillis());

				/**
				 * [talendJobLog end ] stop
				 */
			} // end the resume

		} catch (java.lang.Exception e) {

			if (!(e instanceof TalendException)) {
				log.fatal(currentComponent + " " + e.getMessage(), e);
			}

			TalendException te = new TalendException(e, currentComponent, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			try {

				/**
				 * [talendJobLog finally ] start
				 */

				currentComponent = "talendJobLog";

				/**
				 * [talendJobLog finally ] stop
				 */
			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("talendJobLog_SUBPROCESS_STATE", 1);
	}

	public String resuming_logs_dir_path = null;
	public String resuming_checkpoint_path = null;
	public String parent_part_launcher = null;
	private String resumeEntryMethodName = null;
	private boolean globalResumeTicket = false;

	public boolean watch = false;
	// portStats is null, it means don't execute the statistics
	public Integer portStats = null;
	public int portTraces = 4334;
	public String clientHost;
	public String defaultClientHost = "localhost";
	public String contextStr = "Dev";
	public boolean isDefaultContext = true;
	public String pid = "0";
	public String rootPid = null;
	public String fatherPid = null;
	public String fatherNode = null;
	public long startTime = 0;
	public boolean isChildJob = false;
	public String log4jLevel = "";

	private boolean enableLogStash;

	private boolean execStat = true;

	private ThreadLocal<java.util.Map<String, String>> threadLocal = new ThreadLocal<java.util.Map<String, String>>() {
		protected java.util.Map<String, String> initialValue() {
			java.util.Map<String, String> threadRunResultMap = new java.util.HashMap<String, String>();
			threadRunResultMap.put("errorCode", null);
			threadRunResultMap.put("status", "");
			return threadRunResultMap;
		};
	};

	private PropertiesWithType context_param = new PropertiesWithType();
	public java.util.Map<String, Object> parentContextMap = new java.util.HashMap<String, Object>();

	public String status = "";

	public static void main(String[] args) {
		final AddDatasetFromTDC_04 AddDatasetFromTDC_04Class = new AddDatasetFromTDC_04();

		int exitCode = AddDatasetFromTDC_04Class.runJobInTOS(args);
		if (exitCode == 0) {
			log.info("TalendJob: 'AddDatasetFromTDC_04' - Done.");
		}

		System.exit(exitCode);
	}

	public String[][] runJob(String[] args) {

		int exitCode = runJobInTOS(args);
		String[][] bufferValue = new String[][] { { Integer.toString(exitCode) } };

		return bufferValue;
	}

	public boolean hastBufferOutputComponent() {
		boolean hastBufferOutput = false;

		return hastBufferOutput;
	}

	public int runJobInTOS(String[] args) {
		// reset status
		status = "";

		String lastStr = "";
		for (String arg : args) {
			if (arg.equalsIgnoreCase("--context_param")) {
				lastStr = arg;
			} else if (lastStr.equals("")) {
				evalParam(arg);
			} else {
				evalParam(lastStr + " " + arg);
				lastStr = "";
			}
		}
		enableLogStash = "true".equalsIgnoreCase(System.getProperty("monitoring"));

		if (!"".equals(log4jLevel)) {

			if ("trace".equalsIgnoreCase(log4jLevel)) {
				org.apache.logging.log4j.core.config.Configurator.setLevel(log.getName(),
						org.apache.logging.log4j.Level.TRACE);
			} else if ("debug".equalsIgnoreCase(log4jLevel)) {
				org.apache.logging.log4j.core.config.Configurator.setLevel(log.getName(),
						org.apache.logging.log4j.Level.DEBUG);
			} else if ("info".equalsIgnoreCase(log4jLevel)) {
				org.apache.logging.log4j.core.config.Configurator.setLevel(log.getName(),
						org.apache.logging.log4j.Level.INFO);
			} else if ("warn".equalsIgnoreCase(log4jLevel)) {
				org.apache.logging.log4j.core.config.Configurator.setLevel(log.getName(),
						org.apache.logging.log4j.Level.WARN);
			} else if ("error".equalsIgnoreCase(log4jLevel)) {
				org.apache.logging.log4j.core.config.Configurator.setLevel(log.getName(),
						org.apache.logging.log4j.Level.ERROR);
			} else if ("fatal".equalsIgnoreCase(log4jLevel)) {
				org.apache.logging.log4j.core.config.Configurator.setLevel(log.getName(),
						org.apache.logging.log4j.Level.FATAL);
			} else if ("off".equalsIgnoreCase(log4jLevel)) {
				org.apache.logging.log4j.core.config.Configurator.setLevel(log.getName(),
						org.apache.logging.log4j.Level.OFF);
			}
			org.apache.logging.log4j.core.config.Configurator
					.setLevel(org.apache.logging.log4j.LogManager.getRootLogger().getName(), log.getLevel());

		}
		log.info("TalendJob: 'AddDatasetFromTDC_04' - Start.");

		if (enableLogStash) {
			java.util.Properties properties_talendJobLog = new java.util.Properties();
			properties_talendJobLog.setProperty("root.logger", "audit");
			properties_talendJobLog.setProperty("encoding", "UTF-8");
			properties_talendJobLog.setProperty("application.name", "Talend Studio");
			properties_talendJobLog.setProperty("service.name", "Talend Studio Job");
			properties_talendJobLog.setProperty("instance.name", "Talend Studio Job Instance");
			properties_talendJobLog.setProperty("propagate.appender.exceptions", "none");
			properties_talendJobLog.setProperty("log.appender", "file");
			properties_talendJobLog.setProperty("appender.file.path", "audit.json");
			properties_talendJobLog.setProperty("appender.file.maxsize", "52428800");
			properties_talendJobLog.setProperty("appender.file.maxbackup", "20");
			properties_talendJobLog.setProperty("host", "false");

			System.getProperties().stringPropertyNames().stream()
					.filter(it -> it.startsWith("monitoring.audit.logger.properties."))
					.forEach(key -> properties_talendJobLog.setProperty(
							key.substring("monitoring.audit.logger.properties.".length()), System.getProperty(key)));

			org.apache.logging.log4j.core.config.Configurator.setLevel("audit", org.apache.logging.log4j.Level.DEBUG);

			auditLogger_talendJobLog = org.talend.job.audit.JobEventAuditLoggerFactory
					.createJobAuditLogger(properties_talendJobLog);
		}

		if (clientHost == null) {
			clientHost = defaultClientHost;
		}

		if (pid == null || "0".equals(pid)) {
			pid = TalendString.getAsciiRandomString(6);
		}

		if (rootPid == null) {
			rootPid = pid;
		}
		if (fatherPid == null) {
			fatherPid = pid;
		} else {
			isChildJob = true;
		}

		if (portStats != null) {
			// portStats = -1; //for testing
			if (portStats < 0 || portStats > 65535) {
				// issue:10869, the portStats is invalid, so this client socket can't open
				System.err.println("The statistics socket port " + portStats + " is invalid.");
				execStat = false;
			}
		} else {
			execStat = false;
		}

		try {
			// call job/subjob with an existing context, like: --context=production. if
			// without this parameter, there will use the default context instead.
			java.io.InputStream inContext = AddDatasetFromTDC_04.class.getClassLoader()
					.getResourceAsStream("nssol/adddatasetfromtdc_04_0_1/contexts/" + contextStr + ".properties");
			if (inContext == null) {
				inContext = AddDatasetFromTDC_04.class.getClassLoader()
						.getResourceAsStream("config/contexts/" + contextStr + ".properties");
			}
			if (inContext != null) {
				// defaultProps is in order to keep the original context value
				if (context != null && context.isEmpty()) {
					defaultProps.load(inContext);
					context = new ContextProperties(defaultProps);
				}

				inContext.close();
			} else if (!isDefaultContext) {
				// print info and job continue to run, for case: context_param is not empty.
				System.err.println("Could not find the context " + contextStr);
			}

			if (!context_param.isEmpty()) {
				context.putAll(context_param);
				// set types for params from parentJobs
				for (Object key : context_param.keySet()) {
					String context_key = key.toString();
					String context_type = context_param.getContextType(context_key);
					context.setContextType(context_key, context_type);

				}
			}
			class ContextProcessing {
				private void processContext_0() {
					context.setContextType("datasetName", "id_String");
					context.datasetName = (String) context.getProperty("datasetName");
					context.setContextType("projectName", "id_String");
					context.projectName = (String) context.getProperty("projectName");
					context.setContextType("tcToken", "id_String");
					context.tcToken = (String) context.getProperty("tcToken");
					context.setContextType("tdcPassword", "id_Password");
					String pwd_tdcPassword_value = context.getProperty("tdcPassword");
					context.tdcPassword = null;
					if (pwd_tdcPassword_value != null) {
						if (context_param.containsKey("tdcPassword")) {// no need to decrypt if it come from program
																		// argument or parent job runtime
							context.tdcPassword = pwd_tdcPassword_value;
						} else if (!pwd_tdcPassword_value.isEmpty()) {
							try {
								context.tdcPassword = routines.system.PasswordEncryptUtil
										.decryptPassword(pwd_tdcPassword_value);
								context.put("tdcPassword", context.tdcPassword);
							} catch (java.lang.RuntimeException e) {
								// do nothing
							}
						}
					}
					context.setContextType("tdcRootObjectId", "id_String");
					context.tdcRootObjectId = (String) context.getProperty("tdcRootObjectId");
					context.setContextType("tdcTargetObjectPath", "id_String");
					context.tdcTargetObjectPath = (String) context.getProperty("tdcTargetObjectPath");
					context.setContextType("tdcTargetSchema", "id_String");
					context.tdcTargetSchema = (String) context.getProperty("tdcTargetSchema");
					context.setContextType("tdcURL", "id_String");
					context.tdcURL = (String) context.getProperty("tdcURL");
					context.setContextType("tdcUser", "id_String");
					context.tdcUser = (String) context.getProperty("tdcUser");
					context.setContextType("tdpDatasetName", "id_String");
					context.tdpDatasetName = (String) context.getProperty("tdpDatasetName");
					context.setContextType("tdpId", "id_String");
					context.tdpId = (String) context.getProperty("tdpId");
					context.setContextType("tdpURL", "id_String");
					context.tdpURL = (String) context.getProperty("tdpURL");
					context.setContextType("workingDirectoryPath", "id_Directory");
					context.workingDirectoryPath = (String) context.getProperty("workingDirectoryPath");
				}

				public void processAllContext() {
					processContext_0();
				}
			}

			new ContextProcessing().processAllContext();
		} catch (java.io.IOException ie) {
			System.err.println("Could not load context " + contextStr);
			ie.printStackTrace();
		}

		// get context value from parent directly
		if (parentContextMap != null && !parentContextMap.isEmpty()) {
			if (parentContextMap.containsKey("datasetName")) {
				context.datasetName = (String) parentContextMap.get("datasetName");
			}
			if (parentContextMap.containsKey("projectName")) {
				context.projectName = (String) parentContextMap.get("projectName");
			}
			if (parentContextMap.containsKey("tcToken")) {
				context.tcToken = (String) parentContextMap.get("tcToken");
			}
			if (parentContextMap.containsKey("tdcPassword")) {
				context.tdcPassword = (java.lang.String) parentContextMap.get("tdcPassword");
			}
			if (parentContextMap.containsKey("tdcRootObjectId")) {
				context.tdcRootObjectId = (String) parentContextMap.get("tdcRootObjectId");
			}
			if (parentContextMap.containsKey("tdcTargetObjectPath")) {
				context.tdcTargetObjectPath = (String) parentContextMap.get("tdcTargetObjectPath");
			}
			if (parentContextMap.containsKey("tdcTargetSchema")) {
				context.tdcTargetSchema = (String) parentContextMap.get("tdcTargetSchema");
			}
			if (parentContextMap.containsKey("tdcURL")) {
				context.tdcURL = (String) parentContextMap.get("tdcURL");
			}
			if (parentContextMap.containsKey("tdcUser")) {
				context.tdcUser = (String) parentContextMap.get("tdcUser");
			}
			if (parentContextMap.containsKey("tdpDatasetName")) {
				context.tdpDatasetName = (String) parentContextMap.get("tdpDatasetName");
			}
			if (parentContextMap.containsKey("tdpId")) {
				context.tdpId = (String) parentContextMap.get("tdpId");
			}
			if (parentContextMap.containsKey("tdpURL")) {
				context.tdpURL = (String) parentContextMap.get("tdpURL");
			}
			if (parentContextMap.containsKey("workingDirectoryPath")) {
				context.workingDirectoryPath = (String) parentContextMap.get("workingDirectoryPath");
			}
		}

		// Resume: init the resumeUtil
		resumeEntryMethodName = ResumeUtil.getResumeEntryMethodName(resuming_checkpoint_path);
		resumeUtil = new ResumeUtil(resuming_logs_dir_path, isChildJob, rootPid);
		resumeUtil.initCommonInfo(pid, rootPid, fatherPid, projectName, jobName, contextStr, jobVersion);

		List<String> parametersToEncrypt = new java.util.ArrayList<String>();
		parametersToEncrypt.add("tdcPassword");
		// Resume: jobStart
		resumeUtil.addLog("JOB_STARTED", "JOB:" + jobName, parent_part_launcher, Thread.currentThread().getId() + "",
				"", "", "", "", resumeUtil.convertToJsonText(context, parametersToEncrypt));

		if (execStat) {
			try {
				runStat.openSocket(!isChildJob);
				runStat.setAllPID(rootPid, fatherPid, pid, jobName);
				runStat.startThreadStat(clientHost, portStats);
				runStat.updateStatOnJob(RunStat.JOBSTART, fatherNode);
			} catch (java.io.IOException ioException) {
				ioException.printStackTrace();
			}
		}

		java.util.concurrent.ConcurrentHashMap<Object, Object> concurrentHashMap = new java.util.concurrent.ConcurrentHashMap<Object, Object>();
		globalMap.put("concurrentHashMap", concurrentHashMap);

		long startUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		long endUsedMemory = 0;
		long end = 0;

		startTime = System.currentTimeMillis();

		this.globalResumeTicket = true;// to run tPreJob

		if (enableLogStash) {
			talendJobLog.addJobStartMessage();
			try {
				talendJobLogProcess(globalMap);
			} catch (java.lang.Exception e) {
				e.printStackTrace();
			}
		}

		this.globalResumeTicket = false;// to run others jobs

		try {
			errorCode = null;
			tRESTClient_1Process(globalMap);
			if (!"failure".equals(status)) {
				status = "end";
			}
		} catch (TalendException e_tRESTClient_1) {
			globalMap.put("tRESTClient_1_SUBPROCESS_STATE", -1);

			e_tRESTClient_1.printStackTrace();

		}

		this.globalResumeTicket = true;// to run tPostJob

		end = System.currentTimeMillis();

		if (watch) {
			System.out.println((end - startTime) + " milliseconds");
		}

		endUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		if (false) {
			System.out.println(
					(endUsedMemory - startUsedMemory) + " bytes memory increase when running : AddDatasetFromTDC_04");
		}
		if (enableLogStash) {
			talendJobLog.addJobEndMessage(startTime, end, status);
			try {
				talendJobLogProcess(globalMap);
			} catch (java.lang.Exception e) {
				e.printStackTrace();
			}
		}

		if (execStat) {
			runStat.updateStatOnJob(RunStat.JOBEND, fatherNode);
			runStat.stopThreadStat();
		}
		int returnCode = 0;
		if (errorCode == null) {
			returnCode = status != null && status.equals("failure") ? 1 : 0;
		} else {
			returnCode = errorCode.intValue();
		}
		resumeUtil.addLog("JOB_ENDED", "JOB:" + jobName, parent_part_launcher, Thread.currentThread().getId() + "", "",
				"" + returnCode, "", "", "");

		return returnCode;

	}

	// only for OSGi env
	public void destroy() {

	}

	private java.util.Map<String, Object> getSharedConnections4REST() {
		java.util.Map<String, Object> connections = new java.util.HashMap<String, Object>();

		return connections;
	}

	private void evalParam(String arg) {
		if (arg.startsWith("--resuming_logs_dir_path")) {
			resuming_logs_dir_path = arg.substring(25);
		} else if (arg.startsWith("--resuming_checkpoint_path")) {
			resuming_checkpoint_path = arg.substring(27);
		} else if (arg.startsWith("--parent_part_launcher")) {
			parent_part_launcher = arg.substring(23);
		} else if (arg.startsWith("--watch")) {
			watch = true;
		} else if (arg.startsWith("--stat_port=")) {
			String portStatsStr = arg.substring(12);
			if (portStatsStr != null && !portStatsStr.equals("null")) {
				portStats = Integer.parseInt(portStatsStr);
			}
		} else if (arg.startsWith("--trace_port=")) {
			portTraces = Integer.parseInt(arg.substring(13));
		} else if (arg.startsWith("--client_host=")) {
			clientHost = arg.substring(14);
		} else if (arg.startsWith("--context=")) {
			contextStr = arg.substring(10);
			isDefaultContext = false;
		} else if (arg.startsWith("--father_pid=")) {
			fatherPid = arg.substring(13);
		} else if (arg.startsWith("--root_pid=")) {
			rootPid = arg.substring(11);
		} else if (arg.startsWith("--father_node=")) {
			fatherNode = arg.substring(14);
		} else if (arg.startsWith("--pid=")) {
			pid = arg.substring(6);
		} else if (arg.startsWith("--context_type")) {
			String keyValue = arg.substring(15);
			int index = -1;
			if (keyValue != null && (index = keyValue.indexOf('=')) > -1) {
				if (fatherPid == null) {
					context_param.setContextType(keyValue.substring(0, index),
							replaceEscapeChars(keyValue.substring(index + 1)));
				} else { // the subjob won't escape the especial chars
					context_param.setContextType(keyValue.substring(0, index), keyValue.substring(index + 1));
				}

			}

		} else if (arg.startsWith("--context_param")) {
			String keyValue = arg.substring(16);
			int index = -1;
			if (keyValue != null && (index = keyValue.indexOf('=')) > -1) {
				if (fatherPid == null) {
					context_param.put(keyValue.substring(0, index), replaceEscapeChars(keyValue.substring(index + 1)));
				} else { // the subjob won't escape the especial chars
					context_param.put(keyValue.substring(0, index), keyValue.substring(index + 1));
				}
			}
		} else if (arg.startsWith("--log4jLevel=")) {
			log4jLevel = arg.substring(13);
		} else if (arg.startsWith("--monitoring") && arg.contains("=")) {// for trunjob call
			final int equal = arg.indexOf('=');
			final String key = arg.substring("--".length(), equal);
			System.setProperty(key, arg.substring(equal + 1));
		}
	}

	private static final String NULL_VALUE_EXPRESSION_IN_COMMAND_STRING_FOR_CHILD_JOB_ONLY = "<TALEND_NULL>";

	private final String[][] escapeChars = { { "\\\\", "\\" }, { "\\n", "\n" }, { "\\'", "\'" }, { "\\r", "\r" },
			{ "\\f", "\f" }, { "\\b", "\b" }, { "\\t", "\t" } };

	private String replaceEscapeChars(String keyValue) {

		if (keyValue == null || ("").equals(keyValue.trim())) {
			return keyValue;
		}

		StringBuilder result = new StringBuilder();
		int currIndex = 0;
		while (currIndex < keyValue.length()) {
			int index = -1;
			// judege if the left string includes escape chars
			for (String[] strArray : escapeChars) {
				index = keyValue.indexOf(strArray[0], currIndex);
				if (index >= 0) {

					result.append(keyValue.substring(currIndex, index + strArray[0].length()).replace(strArray[0],
							strArray[1]));
					currIndex = index + strArray[0].length();
					break;
				}
			}
			// if the left string doesn't include escape chars, append the left into the
			// result
			if (index < 0) {
				result.append(keyValue.substring(currIndex));
				currIndex = currIndex + keyValue.length();
			}
		}

		return result.toString();
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public String getStatus() {
		return status;
	}

	ResumeUtil resumeUtil = null;
}
/************************************************************************************************
 * 210721 characters generated by Talend Cloud Data Management Platform on the
 * 2021/04/09 11:06:08 JST
 ************************************************************************************************/