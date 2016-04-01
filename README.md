# Kerlink-EMS on autoproduction

Four  web services are defined for Kerlink-EMS  on autoproduction including PV Panel, Battery , CarChargingPoint  and forecast estimation  web services. These services are running within an EMS server to interact with energy units and devices like PV Panel and Battery.  In the next release, the operations will also be able to consume or produce RDF, in conformance with the SEAS knowledge model.SPARQL-Generate protocol will be used to describe how RDF may be generated from XML.

# EMS Server

The EMS  Server defines two interactions with Clients :
+ Client requests for the execution of the charge optimization algorithm
+ Client requests for the result of the algorithm

## Description of EMS Server 

Node: EMS

|interacts as:                | Server|
|-----------------------------|-----------------------------------------------------------------|
|with:                        | Energy Unit including PV node, Battery node, ChargingPoint node |
|processes requests for some: |	Algorithm for providing optimal way to control the usage profile of devices | 
|about/on resource:	| Usage Control algorithm where the objective is to determine an equilibrium price for the energy. |
|expected message content:    | Device “State” -> preferred format --> XML |
|preferred request format:    | -> preferred format --> XML |
|response format:	     | The powermatcher server provides “Control Parameters” which define the usage profile of the devices,i.e. when a device should start or stop. -> preferred format --> XML | 					

# PV Panel

Two Restful APIs (POST and GET) are provided to consume and produce parameters regarding production of PV panels. These APIs generates XML as descibed below. In the next release, these APIs will be able to use RDF, in conformance with the SEAS knowledge model.

+ `POST`:
`curl -X POST -d "ts={ts}&device_id={device_id}&production={production}" http://osm.procan-group.com/PvPanel/rest/pvs` :  the {ts} parameter represents the timestamp of the pv production, the {device_id} parameter represents the id of the pvpanel, the {production} parameter represents the energy production value. Then the server returns a 202 Accepted.

+ `GET`:
`curl -X GET http://osm.procan-group.com/PvPanel/rest/pvs/{device_id}` :the {device_id} parameter represents the id of the pvpanel. Then if the server returns a 200 OK, the HTTP body represents the response and includes the production of a given PV Panel (see below)

```
<PvPanel>
  <Device>
     <ts>1458001200</ts>
     <DeviceId>00031614</DeviceId>
     <Production>1916.85</Production>
   </Device>
<PvPanel>
```


`curl -X GET http://osm.procan-group.com/PvPanel/rest/pvs/` : if the server returns a 200 OK, the HTTP body includes all PV Panels and their productions (see below)

```
<PvPanel>
	<Device>
		<ts>1458001200</ts>
		<DeviceId>00031614</DeviceId>
		<Production>1916.85</Production>
	</Device>
	<Device>
		<ts>1458001800</ts>
		<DeviceId>00031614</DeviceId>
		<Production>2570.83</Production>
	</Device>
	<Device>
		<ts>1458002400</ts>
		<DeviceId>00031614</DeviceId>
		<Production>3196.07</Production>
	</Device>
	<Device>
		<ts>1458003000</ts>
		<DeviceId>00031614</DeviceId>
		<Production>3761.39</Production>
	</Device>
	<Device>
	<ts>1458003600</ts>
		<DeviceId>00031614</DeviceId>
		<Production>4300.34</Production>
	</Device>
</PvPanel>
```

# Battery

 Two Restful APIs (POST and GET) are provided to consume and produce parameters regarding  the state of Battery. These APIs generates XML as descibed below. In the next release, these APIs will be able to use RDF, in conformance with the SEAS knowledge model.
+ `POST`: 
`curl -X POST -d "ts={ts}&device_id={device_id}&state={state} &stateOfCharge={stateOfCharge}" http://osm.procangroup.com/Battery/rest/batt` : the {ts} parameter represents the timestamp of the battery charging or discharging, the {device_id} parameter represents the id of the battery, the { state } parameter represents the  state of battery (charging or discharging) and  the {stateOfCharge} parameter represents the percentage  of charge. Then the server returns a 202 Accepted.
+ `GET`: 
`curl -X GET http://osm.procan-group.com/Battery/rest/batt/{device_id} ` : the {device_id} parameter represents the id of the  battery. Then if the server returns a 200 OK, the HTTP body represents the response and includes the  state of a given Battery (see below)
```
<Battery>
  <Device>
     <ts>1458001200</ts>
     <DeviceId>00031614</DeviceId>
     <State>charhing</State>
     <StateOfCharge>0.3</StateOfCharge>
   </Device>
</Battery>
```
`curl -X GET http://osm.procan-group.com/Battery/rest/batt/` : if the server returns a 200 OK, the HTTP body includes all batteries and their states (see below)
```
<Battery>
    <Device>
        <ts>1458001200</ts>
        <DeviceId>00031614</DeviceId>
        <State>charhing</State>
        <StateOfCharge>0.3</StateOfCharge>
    </Device>
    <Device>
        <ts>1458001800</ts>
        <DeviceId>00031614</DeviceId>
        <State>charhing</State>
        <StateOfCharge>0.4</StateOfCharge>
    </Device>
    <Device>
        <ts>1458002400</ts>
        <DeviceId>00031614</DeviceId>
        <State>discharhing</State>
        <StateOfCharge>0.7</StateOfCharge>
    </Device>
    <Device>
        <ts>1458003000</ts>
        <DeviceId>00031614</DeviceId>
        <State>discharhing</State>
        <StateOfCharge>0.5</StateOfCharge>
    </Device>
</Battery>
```

# CarChargingPoint
 Two Restful APIs (POST and GET) are provided to consume and produce parameters regarding  charging of  the car . These APIs generates XML as descibed below. In the next release, these APIs will be able to use RDF, in conformance with the SEAS knowledge model.
+ `POST`: 
`curl -X POST -d "ts={ts}&device_id={device_id}&consumption={ consumption}" http://osm.procan-group.com/CarCharging/rest/point `: the {ts} parameter represents the timestamp of the car  charging, the {device_id} parameter represents the id of the charging point , the {consumption} parameter represents the energy consumption  value. Then the server returns a 202 Accepted.
+ `GET`: 
`curl -X GET http://osm.procan- group.com/CarCharging/rest/point/{device_id} `: the {device_id} parameter represents the id of the charging point. Then if the server returns a 200 OK, the HTTP body represents the response and includes the consumption of a given  charging point (see below)
```
<CarChargingPoint>
  <Device>
     <ts>1458001200</ts>
     <DeviceId>00031614</DeviceId>
     <Consumption>1916.85</Consumption>
   </Device>
</CarChargingPoint>
```
`curl -X GET http://osm.procan-group.com/CarCharging/rest/point/` : if the server returns a 200 OK, the HTTP body includes all  car charging points  and their  consumptions (see below)
```
<CarChargingPoint>
    <Device>
        <ts>1458001200</ts>
        <DeviceId>00031614</DeviceId>
        <Consumption>1916.85</Consumption>
    </Device>
    <Device>
        <ts>1458001800</ts>
        <DeviceId>00031614</DeviceId>
        <Consumption>2570.83</Consumption>
    </Device>
    <Device>
        <ts>1458002400</ts>
        <DeviceId>00031614</DeviceId>
        <Consumption>3196.07</Consumption>
    </Device>
    <Device>
        <ts>1458003000</ts>
        <DeviceId>00031614</DeviceId>
        <Consumption>3761.39</Consumption>
    </Device>
</CarChargingPoint>
```
# Forecast Estimation 
 The Restful API  (GET)  is provided to consume and produce parameters regarding  forecast estimation . This  API  generates XML as descibed below. In the next release, this  API  will be able to use RDF, in conformance with the SEAS knowledge model.
+ `GET`:
`curl -X GET http://osm.procan-group.com/ForecastEstimation/rest/estimate/{position}` : the  {position} parameter represents the city name. Then if the server returns a  200 OK, the HTTP body includes all the forecasting parameters (see below)
```
<ForecastEstimation>
      <Temperature>6.2</Temperature>
     <Wind>5.8</Wind>
     <Humidity>7.9</Humidity>
</ForecastEstimation>
```

 
