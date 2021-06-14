
package nssol.test_0_1;

import routines.DataOperation;
import routines.TalendDataGenerator;
import routines.DataQuality;
import routines.Relational;
import routines.DataQualityDependencies;
import routines.Mathematical;
import routines.SQLike;
import routines.Numeric;
import routines.TalendStringUtil;
import routines.TalendString;
import routines.DQTechnical;
import routines.StringHandling;
import routines.DataMasking;
import routines.TalendDate;
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
 * Job: test Purpose: <br>
 * Description: <br>
 * 
 * @author tanaka, tanaka
 * @version 7.3.1.20200219_1130
 * @status
 */
public class test implements TalendJob {
	static {
		System.setProperty("TalendJob.log", "test.log");
	}

	private static org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager.getLogger(test.class);

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

		}

	}

	protected ContextProperties context = new ContextProperties(); // will be instanciated by MS.

	public ContextProperties getContext() {
		return this.context;
	}

	private final String jobVersion = "0.1";
	private final String jobName = "test";
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

	JobStructureCatcherUtils talendJobLog = new JobStructureCatcherUtils(jobName, "_yWbJAJj4Eeucp-jtzVVvzg", "0.1");
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
					test.this.exception = e;
				}
			}
			if (!(e instanceof TalendException)) {
				try {
					for (java.lang.reflect.Method m : this.getClass().getEnclosingClass().getMethods()) {
						if (m.getName().compareTo(currentComponent + "_error") == 0) {
							m.invoke(test.this, new Object[] { e, currentComponent, globalMap });
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

	public void talendJobLog_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

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

// expected response body
				javax.ws.rs.core.Response responseDoc_tRESTClient_1 = null;

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

					String url = "https://tdp.ap.cloud.talend.com/sharing/v1/sharingset/dataset/42f8c8f3-d52a-4105-aff3-b1acc30aa097";
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
										"enc:routine.encryption.key.v1:2wEjLXLkfhSPeKFMcUBfLMwnyYYQl2G3eEeDaFJog95uNaSud4JAvlkwKx/adgmAyr5CoBgzoIeCV1lC2apFzNZLqiNTkx58Uhk0TUqxiZP1QcFamVu14CDzMPg=");

						org.apache.cxf.ws.security.trust.STSClient stsClient = org.talend.esb.security.saml.STSClientUtils
								.createSTSClient(factoryBean_tRESTClient_1.getBus(), "username",
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
										"enc:routine.encryption.key.v1:cxADIx4vnkj9fqRXJ44edO+zIya2r5m0z51D1giuAvy/wQGnbLqFBhtGt6vtujvEIf6+kCdaHkEoDdWeXzRmENwgeUAceYIuR7vOySNJC98XnXCbyvyMXDx9FeY=");

						org.apache.cxf.configuration.security.AuthorizationPolicy authPolicy_tRESTClient_1 = new org.apache.cxf.configuration.security.AuthorizationPolicy();
						authPolicy_tRESTClient_1.setAuthorizationType("Basic");
						authPolicy_tRESTClient_1.setUserName("username");
						authPolicy_tRESTClient_1.setPassword(decryptedPassword_tRESTClient_1);
						conduit_tRESTClient_1.setAuthorization(authPolicy_tRESTClient_1);
					} else if (use_auth_tRESTClient_1 && "HTTP Digest".equals("OAUTH2_BEARER")) {
						// set Digest auth

						final String decryptedPassword_tRESTClient_1 = routines.system.PasswordEncryptUtil
								.decryptPassword(
										"enc:routine.encryption.key.v1:bXRaPXuPzMXTdBigXPODxH06kiGrkaDwIvB4rnd0HditVlaEIrPdsQvCphYj3BXWOm0EqM5H2VvwtwQBvQKswLSUJDkaRojemDA16c+fkzOOfPgRniFMjkiHauU=");

						org.apache.cxf.configuration.security.AuthorizationPolicy authPolicy_tRESTClient_1 = new org.apache.cxf.configuration.security.AuthorizationPolicy();
						authPolicy_tRESTClient_1.setAuthorizationType("Digest");
						authPolicy_tRESTClient_1.setUserName("username");
						authPolicy_tRESTClient_1.setPassword(decryptedPassword_tRESTClient_1);
						conduit_tRESTClient_1.setAuthorization(authPolicy_tRESTClient_1);
					}

					if (!inOSGi) {
						conduit_tRESTClient_1.getClient().setReceiveTimeout((long) (60 * 1000L));
						conduit_tRESTClient_1.getClient().setConnectionTimeout((long) (30 * 1000L));
						boolean use_proxy_tRESTClient_1 = false;

					}

					// set Content-Type
					webClient_tRESTClient_1.type("application/json");

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
										"enc:routine.encryption.key.v1:+lUzoww9WcYu0UC2LxIDFp1zqXGuFArjk2TxOwT3T2f9jfnkStLW1Vg/IO3cEl31682LEA15hJCEZmLZQG8TQHmE+PgO1LQKPttldZctIuBq5XPXztRSWavbt6U=");

						String username_tRESTClient_1 = "username";
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

						org.apache.cxf.jaxrs.client.WebClient.getConfig(webClient_tRESTClient_1).getRequestContext()
								.put("use.httpurlconnection.method.reflection", true);
						responseDoc_tRESTClient_1 = webClient_tRESTClient_1.invoke("PATCH", requestBody_tRESTClient_1);

						int webClientResponseStatus_tRESTClient_1 = webClient_tRESTClient_1.getResponse().getStatus();
						if (webClientResponseStatus_tRESTClient_1 >= 300) {
							throw new javax.ws.rs.WebApplicationException(webClient_tRESTClient_1.getResponse());
						}

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
				 * [tRESTClient_1 finally ] start
				 */

				currentComponent = "tRESTClient_1";

				/**
				 * [tRESTClient_1 finally ] stop
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
	public String contextStr = "Default";
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
		final test testClass = new test();

		int exitCode = testClass.runJobInTOS(args);
		if (exitCode == 0) {
			log.info("TalendJob: 'test' - Done.");
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
		log.info("TalendJob: 'test' - Start.");

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
			java.io.InputStream inContext = test.class.getClassLoader()
					.getResourceAsStream("nssol/test_0_1/contexts/" + contextStr + ".properties");
			if (inContext == null) {
				inContext = test.class.getClassLoader()
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
		}

		// Resume: init the resumeUtil
		resumeEntryMethodName = ResumeUtil.getResumeEntryMethodName(resuming_checkpoint_path);
		resumeUtil = new ResumeUtil(resuming_logs_dir_path, isChildJob, rootPid);
		resumeUtil.initCommonInfo(pid, rootPid, fatherPid, projectName, jobName, contextStr, jobVersion);

		List<String> parametersToEncrypt = new java.util.ArrayList<String>();
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
			System.out.println((endUsedMemory - startUsedMemory) + " bytes memory increase when running : test");
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
 * 43576 characters generated by Talend Cloud Data Management Platform on the
 * 2021/04/09 15:05:34 JST
 ************************************************************************************************/