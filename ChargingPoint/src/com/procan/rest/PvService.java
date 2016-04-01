package com.procan.rest;

public class PvService {
	PVDao pvDao;

	public PvService() {

		this.pvDao = new PVDao();
	}

	public void AddPV(long ts, String device_id, double production) {

		pvDao.savePV(ts, device_id, production);
	}

	public String getPVList() {
		return pvDao.getAllPV();

	}

	public String getPv(String id) {
		return pvDao.getPv(id);
	}
}
