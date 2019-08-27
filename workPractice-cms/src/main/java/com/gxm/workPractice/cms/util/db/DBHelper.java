package com.gxm.workPractice.cms.util.db;

public class DBHelper {

	public enum DBSource {
		BASE("baseDataSource"), TMPPOPULATION("tmpPopulationDataSource");
		
		private final String info;

		private DBSource(String info) {
			this.info = info;
		}

		public String getInfo() {
			System.out.println(info);
			return info;
		}
	}

	public static void configureDB(DBSource db) {
		switch (db) {
		case BASE:
			DatabaseContextHolder.setCustomerType(DBSource.BASE.getInfo());
			// 基础数据库
			break;
		case TMPPOPULATION:
			DatabaseContextHolder.setCustomerType(DBSource.TMPPOPULATION.getInfo());
			// 临时人口数据库
			break;
		default:
			break;
		}
	}
	
	public static void configureDB(String db) {
		Integer code = null;
		if (db.contains("KY")) {
			code = Integer.valueOf(db.substring(2, 6));
		} else if(db.contains("K")) {
			code = Integer.valueOf(db.substring(1, 5));
		} else {
		    code = Integer.valueOf(db.substring(0, 4));
		}
		switch (code) {
		case 3401:
			DatabaseContextHolder.setCustomerType(DBSource.BASE.getInfo());
			// 基础数据库
			break;
		case 3402:
			DatabaseContextHolder.setCustomerType(DBSource.TMPPOPULATION.getInfo());
			// 临时人口数据库
			break;
		default:
			break;
		}
	}

	public static void main(String[] args) {
		configureDB("ky3401050");
	}

}
