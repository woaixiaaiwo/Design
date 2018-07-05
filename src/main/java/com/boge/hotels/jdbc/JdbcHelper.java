package com.boge.hotels.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;

import com.boge.hotels.pojo.HotelInfo;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class JdbcHelper {

	public static  List<?> queryList() throws SQLException{
		
		Connection connection = ConnectionFactory.getConn();
		
		String sql = "select * from hotel_info";
		PreparedStatement statement = (PreparedStatement) connection.prepareStatement(sql);
		ResultSet rs = statement.executeQuery();
		List<HotelInfo> list = new ArrayList<>();
		while (rs.next()) {
			HotelInfo hotelInfo = new HotelInfo();
			hotelInfo.setTitle(rs.getString("title"));
			hotelInfo.setLat(rs.getString("lat"));
			hotelInfo.setLon(rs.getString("lon"));
			list.add(hotelInfo);
        }
		return list;
	}
	
	public static void batchInsert(List<HotelInfo> list) throws SQLException{
		if(CollectionUtils.isEmpty(list))return;
		Connection connection = ConnectionFactory.getConn();
		
		StringBuilder sqlBuilder = new StringBuilder("insert into hotel_info values ");
		for(int i=0;i<list.size();i++){
			HotelInfo hotelInfo = list.get(i);
			sqlBuilder.append("(");
			sqlBuilder.append("\'"+hotelInfo.getTitle()+"\',");
			sqlBuilder.append("\'"+hotelInfo.getPosition()+"\',");
			sqlBuilder.append("\'"+hotelInfo.getPrice()+"\',");
			sqlBuilder.append("\'"+hotelInfo.getRating()+"\',");
			sqlBuilder.append("\'"+hotelInfo.getProps()+"\',");
			sqlBuilder.append("\'"+hotelInfo.getLon()+"\',");
			sqlBuilder.append("\'"+hotelInfo.getLat()+"\'");
			sqlBuilder.append(")");
			if(i < list.size()-1){
				sqlBuilder.append(",");
			}
		}
		PreparedStatement statement = (PreparedStatement) connection.prepareStatement(sqlBuilder.toString());
		System.out.println("插入"+statement.executeUpdate()+"条数据!");
		
	}
	
	public static void batchUpdate(List<HotelInfo> list) throws SQLException{
		if(CollectionUtils.isEmpty(list))return;
		Connection connection = ConnectionFactory.getConn();
		
		String sqlBuilder = "UPDATE hotel_info SET airport_bus_distance = ?,airport_bus_time = ?,airport_taxi_distance = ?,airport_taxi_time = ?,"+
 "hotel_bus_distance = ?,hotel_bus_time = ?,hotel_taxi_distance = ?,hotel_taxi_time = ? WHERE title = ?";
		PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(sqlBuilder);
		for(int i=0;i<list.size();i++){
			HotelInfo hotelInfo = list.get(i);
			preparedStatement.setString(1,hotelInfo.getAirportBusDistance());
			preparedStatement.setString(2,hotelInfo.getAirportBusTime());
			preparedStatement.setString(3,hotelInfo.getAirportTaxiDistance());
			preparedStatement.setString(4,hotelInfo.getAirportTaxiTime());
			preparedStatement.setString(5,hotelInfo.getHotelBusDistance());
			preparedStatement.setString(6,hotelInfo.getHotelBusTime());
			preparedStatement.setString(7,hotelInfo.getHotelTaxiDistance());
			preparedStatement.setString(8,hotelInfo.getHotelTaxiTime());
			preparedStatement.setString(9,hotelInfo.getTitle());
			preparedStatement.executeUpdate();
		}
		System.out.println("更新"+list.size()+"条数据!");
		
	}
	
	public static void main(String[] args) throws SQLException {
		System.out.println(queryList());
	}
	
}
