var title = "Quokka Status";
var devices = [
               {"group":"BEAM STATUS", 
            	   "items":[{"classId":"plc_tertiary", "deviceId":"plc_tertiary", "title":"Sample Shutter", "units":""},
            	            {"classId":"fastshutter", "deviceId":"fastshutter", "title":"Fast Shutter", "units":""},
            	            {"classId":"reactor_power", "deviceId":"reactor_power", "title":"Reactor Power", "units":"MW"},  
            	            {"classId":"cns_inlet_temp", "deviceId":"cns_inlet_temp", "title":"Inlet Temp", "units":"K"},
            	            {"classId":"cns_outlet_temp", "deviceId":"cns_outlet_temp", "title":"Outlet Temp", "units":"K"}
            	            ]
               },
               {"group":"NEUTRON BEAM", 
            	   "items":[{"classId":"monitor_counts", "deviceId":"monitor_counts", "title":"Monitor", "units":"cts"}, 
            	            {"classId":"wavelength_nominal", "deviceId":"/instrument/velocity_selector/wavelength_nominal", "title":"Wavelength", "units":"Å"}, 
            	            {"classId":"aspeed", "deviceId":"/instrument/velocity_selector/aspeed", "title":"Velocity Selector", "units":"rpm"}, 
            	            {"classId":"total_detector_rate", "deviceId":"::histogram_memory::ratemap_xy_total", "title":"Tot. Rate on Detector", "units":""}, 
            	            {"classId":"xy_max_binrate", "deviceId":"::histogram_memory::ratemap_xy_max_bin", "title":"Max Rate on Pixel", "units":""}
            	            ]
               },
               {"group":"INSTRUMENT CONFIGURATION", 
            	   "items":[{"classId":"l1", "deviceId":"l1", "title":"L1", "units":"mm"},
            	            {"classId":"l2", "deviceId":"l2", "title":"L2", "units":"mm"}, 
            	            {"classId":"guide", "deviceId":"/commands/optics/guide/configuration", "title":"Guide", "units":""}
            	            ]
               },
               {"group":"SAMPLE", 
            	   "items":[{"classId":"sampleNum", "deviceId":"sampleNum", "title":"Sample Position", "units":""},  
            	            {"classId":"samplename", "deviceId":"samplename", "title":"Sample Name", "units":""}
            	   ]
               }
               ];

var histmemUrl = "dae/rest/image?type=TOTAL_HISTOGRAM_XY&screen_size_x=600&screen_size_y=600";