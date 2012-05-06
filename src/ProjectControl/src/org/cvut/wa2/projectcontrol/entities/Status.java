package org.cvut.wa2.projectcontrol.entities;

public enum Status {
	waiting {
		public String getString() {
			return "Waiting";
		}
	}
	, processing{
		public String getString() {
			return "Processing";
		}
	}, 
	finished{
		public String getString() {
			return "Finished";
		}
	}, 
	problem{
		public String getString() {
			return "Problem";
		}
	};
	
	
	
	
	
	
	
	public String getString() {return "";};
}
