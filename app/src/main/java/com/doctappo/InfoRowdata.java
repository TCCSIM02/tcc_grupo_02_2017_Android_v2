package com.doctappo;

public class InfoRowdata {
	 	public boolean isclicked=false;
	    public int index;
	    public String serviceid;
	    public String strAmount;
	    public String servicename;
	    public String strDiscount;
		public String strTime;
	    public InfoRowdata(boolean isclicked,int index,String serviceid,String strAmount,String serviceName, String discount, String time)
	    {
	        this.index=index;
	        this.isclicked=isclicked;
	        this.serviceid = serviceid;
	        /*this.fanId=fanId;*/
	        this.strAmount=strAmount;
	        this.servicename = serviceName;
			this.strDiscount = discount;
			this.strTime = time;
	    }
	    
	    public int getIndex()
	    {
	    	return index;
	    }
	    public void setIndex(int index) {
			this.index = index;
		}
	    
	    public String getAmount()
	    {
	    	return strAmount;
	    }
	    public void setAmount(String amount) {
			this.strAmount = amount;
		}
	    
	    public String getServiceId()
	    {
	    	return serviceid;
	    }
	    public void setServiceId(String id) {
			this.serviceid = id;
		}
	    
	    public String getServiceName()
	    {
	    	return servicename;
	    }
	    public void setServiceName(String service) {
			this.servicename = service;
		}

	public String getDiscount()
	{
		return strDiscount;
	}
	public void setDiscount(String discount) {
		this.strDiscount = discount;
	}


	public String getTime()
	{
		return strTime;
	}
	public void setTime(String time) {
		this.strTime = time;
	}


	public boolean isChecked() {
	    	  return isclicked;
	    }
	    public void setChecked(boolean selected) {
	    	  this.isclicked = selected;
	    }
	    	  
	    
	    
}
