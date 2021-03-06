package de.kbs.so0374jc;


public class SpringValues {

	private static SpringValues  springValues	= null;
	
	private String		dbName;
	private String		dbPort;
	private String 		user;
	private String		pw;
	private String		schema;
	private String[] 	scanPackages;

	private String		dbms;
	private String		vendor;
	private String		separator;
	private String		addr;
	private String		driverClass; 		
	private String 		dbPlatform;
	private String 		showSQL;
	
	private SpringValues (String dbName, String dbPort, String user, String pw, String schema, String... scanPackages) {
		this.dbName			= dbName;
		this.dbPort			= dbPort;
		this.user			= user;
		this.pw				= pw;
		this.schema			= schema;
		this.scanPackages	= scanPackages;
		this.addr			= "hostbkn";
		this.dbms			= "db2";
		this.vendor			= "DB2";
		this.separator		= "/";
		
		this.driverClass	= "com.ibm.db2.jcc.DB2DataSource";
		this.dbPlatform		= "org.hibernate.dialect.DB2390Dialect";
		this.showSQL		= "false";
	}
	
	public static SpringValues getSpringValues (String dbName, String dbPort, String user, 
												String pw, String schema, String... scanPackages) {
		springValues			= new SpringValues (dbName, dbPort, user, pw, schema, scanPackages);
		return					springValues;
	}

	public static String getDbName() 			{ return springValues.dbName;		}
	public static String getDbPort() 			{ return springValues.dbPort;		}
	public static String getUser() 				{ return springValues.user;	  		}
	public static String getPw()				{ return springValues.pw;			}
	public static String getSchema() 			{ return springValues.schema;		}
	public static String[] getScanPackages()	{ return springValues.scanPackages;	}
	public static String getAddr() 				{ return springValues.addr;			}
	public static String getDbms() 				{ return springValues.dbms;			}
	public static String getVendor()			{ return springValues.vendor;		}
	public static String getSeparator()			{ return springValues.separator;	}
	public static String getDriverClass() 		{ return springValues.driverClass;	}
	public static String getDbPlatform()		{ return springValues.dbPlatform;	}
	public static String getShowSQL() 			{ return springValues.showSQL;		}
	
	public void setDbms				(String dbms)			{ this.dbms = dbms;					}
	public void setVendor			(String vendor)			{ this.vendor = vendor;				}
	public void setSeparator		(String separator)		{ this.separator = separator;		}
	public void setAddr				(String addr)			{ this.addr = addr;					}
	public void setDriverClass		(String driverClass)	{ this.driverClass = driverClass;	}
	public void setDatabasePlatform	(String dbPlatform) 	{ this.dbPlatform = dbPlatform;		}
	public void setShowSQL			(String showSQL) 		{ this.showSQL = showSQL;			}

}
