CREATE KEYSPACE IF NOT EXISTS rxp3 WITH replication = {'class':'SimpleStrategy', 'replication_factor':1};

use rxp3;

CREATE TABLE rxp3.record (
  key uuid,
  broker text,
  client text,
  recEntity text,
  fund text,
  side text,
  reconType text,
  platform text,
  dimensionType text,
  fields map<text, text>,
  PRIMARY KEY (key)
);
CREATE INDEX record_recEntity_idx ON rxp3.record(recEntity);


CREATE TABLE rxp3.target (
 key uuid,
 broker text,
 client text,
 recEntity text,
 dimensions map<text, text>,
 records set<uuid>,
 PRIMARY KEY (key)
); 
CREATE INDEX target_idx ON rxp3.target(recEntity);

CREATE TABLE rxp3.dimensions_types_by_metadata (
  client text,
  broker text,
  reconType text,
  dimensions_types set<uuid>,
  PRIMARY KEY ((client,broker), reconType)
); 

CREATE TABLE dimensions_table_by_dimentions_type (
 key uuid,
 dimensions_columns set<text>,
 dimensions_composite_columns set<text>,
 dimensions_table text,
 PRIMARY KEY (key)
);

CREATE TABLE rxp3.target_by_recEntity_currcode_dimensions (
  client text,
  broker text,
  recEntity text,
  currcode text,
  setlamtsetl text,
  tmcode text,
  target_key uuid,
  PRIMARY KEY ((recEntity, currcode), setlamtsetl, tmcode)
);

CREATE TABLE rxp3.target_by_recEntity_tmquantity_dimensions (
  client text,
  broker text,
  recEntity text,
  currcode text,
  tmquantity text,
  IMSCUSIP text,
  IMSISIN text,
  IMSSEDOL text,
  tmid text,
  target_key uuid,
  PRIMARY KEY ((recEntity, currcode, tmquantity), IMSCUSIP, IMSISIN, IMSSEDOL, tmid)
);

CREATE TABLE rxp3.target_by_recEntity_setlamtsetl_dimensions (
  client text,
  broker text,
  recEntity text,
  currcode text,
  setlamtsetl text,
  IMSCUSIP text,
  IMSISIN text,
  IMSSEDOL text,
  tmid text,
  target_key uuid,
  PRIMARY KEY ((recEntity, currcode, setlamtsetl), IMSCUSIP, IMSISIN, IMSSEDOL, tmid)
); 

CREATE TABLE rxp3.recordset (
 key uuid,
 side text,
 filename text,
 pathtofile text,
 fields set<text>,
 PRIMARY KEY ((side),key)
);
