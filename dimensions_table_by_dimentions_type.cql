INSERT INTO rxp3.dimensions_table_by_dimentions_type (key, dimensions_columns , dimensions_composite_columns , dimensions_table ) VALUES (8e3cb9a0-b8cc-4333-be25-5826a832717d, { 'currcode' }, { 'setlamtsetl', 'tmcode' }, 'target_by_recEntity_currcode_dimensions');

INSERT INTO rxp3.dimensions_table_by_dimentions_type (key, dimensions_columns , dimensions_composite_columns , dimensions_table ) VALUES (1375238c-79c2-4460-92a3-a220a28f15fb, { 'currcode', 'tmquantity' }, { 'IMSCUSIP', 'IMSISIN', 'IMSSEDOL', 'tmid' }, 'target_by_recEntity_tmquantity_dimensions');

INSERT INTO rxp3.dimensions_table_by_dimentions_type (key, dimensions_columns , dimensions_composite_columns , dimensions_table ) VALUES (76824f23-537b-44dc-bfee-fd8cc6826a01, { 'currcode', 'setlamtsetl' }, { 'IMSCUSIP', 'IMSISIN', 'IMSSEDOL', 'tmid' }, 'target_by_recEntity_setlamtsetl_dimensions');



INSERT INTO rxp3.dimensions_types_by_metadata (client, broker, reconType, dimensions_types) VALUES ('GT', 'GOLD', 'TRANX', { 8e3cb9a0-b8cc-4333-be25-5826a832717d,1375238c-79c2-4460-92a3-a220a28f15fb,76824f23-537b-44dc-bfee-fd8cc6826a01});

