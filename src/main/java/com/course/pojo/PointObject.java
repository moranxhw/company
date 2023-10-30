package com.course.pojo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @author lixuy
 * Created on 2019-04-10
 */
public class PointObject implements Serializable{

    private static final long serialVersionUID = 123456789L;

    private Integer id;
    //成长积分数
    private Integer growScore;
    //可兑换积分数
    private Integer exchangeScore;
    //总积分数
    private Integer scoreTotal;

	//记录血糖
    private Integer bloodSugar;
    
    //年份
    private Integer year;
    //月份
    private Integer month;
	//日期
    private Integer day;
    
    //成长积分评级
    private String rate;
    
    //填写并发症的时间（年）
    private String bfzTime;
    //血糖报告填写次数
    private int countBSugar;
    
    private int isFillInformation = 0;
    
    private Calendar calendar;
    
    private int ydgnNote;

    private int bfzYear = 0;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGrowScore() {
        return growScore;
    }

    public void setGrowScore(Integer growScore) {
        this.growScore = growScore;
    }

    public Integer getExchangeScore() {
        return exchangeScore;
    }

    public void setExchangeScore(Integer exchangeScore) {
        this.exchangeScore = exchangeScore;
    }

    public Integer getScoreTotal() {
        return scoreTotal;
    }

    public void setScoreTotal(Integer scoreTotal) {
        this.scoreTotal = scoreTotal;
    }
    
    public Integer getBloodSugar() {
		return bloodSugar;
	}

	public void setBloodSugar(Integer bloodSugar) {
		this.bloodSugar = bloodSugar;
	}
	
    public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}
	
	public int getIsFillInformation() {
	    return isFillInformation;
	}

	public void setIsFillInformation(int isFillInformation) {
	    this.isFillInformation = isFillInformation;
	}

	public Integer getDay() {
		return day;
	}

	public void setDay(Integer day) {
		this.day = day;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}
	public String getBfzTime() {
	    return bfzTime;
	}

	public void setBfzTime() {
	    SimpleDateFormat sdfLogin = new SimpleDateFormat("yyyy");
	    Timestamp currentTime = new Timestamp(System.currentTimeMillis());
	    //按照格式转换数据
	    String bfzTime = sdfLogin.format(currentTime);
	    this.bfzTime = bfzTime;
	 }

	   public int getCountBSugar() {
	        return countBSugar;
	    }

	    public void setCountBSugar(int countBSugar) {
	        this.countBSugar = countBSugar;
	    }

		public Calendar getCalendar() {
			return calendar;
		}

		public void setCalendar() {
			this.calendar = Calendar.getInstance();
		}

		public int getBfzYear() {
			return bfzYear;
		}

		public void setBfzYear(int bfzYear) {
			this.bfzYear = bfzYear;
		}

		public int getYdgnNote() {
			return ydgnNote;
		}

		public void setYdgnNote(int ydgnNote) {
			this.ydgnNote = ydgnNote;
		}
	
	
}
