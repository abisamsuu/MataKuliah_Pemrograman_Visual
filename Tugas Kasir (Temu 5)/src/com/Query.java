/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com;

/**
 *
 * @author Samsoe-PC
 */
import com.Connect;
import java.sql.*;
import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.text.NumberFormat;
import java.util.Locale;

public class Query {
    private Connection conn = new Connect().connect();
    
    public ResultSet Login(String username){
        try{
            String sql = "SELECT * FROM kasir WHERE nm_kasir = ?";
            PreparedStatement state = this.conn.prepareStatement(sql);
            state.setString(1, username);
            ResultSet result = state.executeQuery();
            if(result.next()){                
                return result;
            }else{
                return null;
            } 
        }catch(Exception err){
            return null;
        }
    }
    
    public boolean InsertDataBarang(String kd_brg,String nm_brg,String jenis, long hg_jual, long hg_beli){
        try{
            String sql = "INSERT INTO barang  (kd_brg,nm_brg,jenis,hargabeli,hargajual) VALUES (?,?,?,?,?)";
            PreparedStatement state = this.conn.prepareStatement(sql);
            state.setString(1,kd_brg);
            state.setString(2,nm_brg);
            state.setString(3,jenis);
            state.setLong(4, hg_beli);
            state.setLong(5,hg_jual);
            int results = state.executeUpdate();
            return results > 0;
            
        }catch(Exception err){
            System.out.println(err);
            return false;
        }
        
    }
    
    public boolean UpdateDataBarang(String kd_brg,String nm_brg,String jenis, long hg_jual, long hg_beli){
        try{
            String sql = "UPDATE barang SET nm_brg=?,jenis=?,hargabeli=?,hargajual=? WHERE kd_brg = ?";
            PreparedStatement state = this.conn.prepareStatement(sql);            
            state.setString(1,nm_brg);
            state.setString(2,jenis);
            state.setLong(3, hg_beli);
            state.setLong(4,hg_jual);
            state.setString(5,kd_brg);
            int results = state.executeUpdate();
            return results > 0;
            
        }catch(Exception err){
            System.out.println(err);
            return false;
        }
        
    }
    
    public boolean InsertDataPelanggan(String id,String nama,String jenis, int telp, String alamat){
        try{
            String sql = "INSERT INTO pelanggan  (nmplgn,jenis,telp,alamat,id_plgn) VALUES (?,?,?,?,?)";
            PreparedStatement state = this.conn.prepareStatement(sql);
            state.setString(1,nama);
            state.setString(2,jenis);
            state.setInt(3,telp);
            state.setString(4, alamat);
            state.setString(5,id);
            int results = state.executeUpdate();
            return results > 0;
            
        }catch(Exception err){
            System.out.println(err);
            return false;
        }
        
    }
    
    public boolean UpdateDataPelanggan(String id,String nama,String jenis, int telp, String alamat){
        try{
            String sql = "UPDATE pelanggan SET nmplgn=?,jenis=?,telp=?,alamat=? WHERE id_plgn = ?";
            PreparedStatement state = this.conn.prepareStatement(sql);
            state.setString(1,nama);
            state.setString(2,jenis);
            state.setInt(3,telp);
            state.setString(4, alamat);
            state.setString(5, id);
            int results = state.executeUpdate();
            return results > 0;
            
        }catch(Exception err){
            System.out.println(err);
            return false;
        }
        
    }
    
    public ResultSet CariPelanggan(String idplgn){
        try{
            String sql = "SELECT * FROM pelanggan WHERE id_plgn = ?";
            PreparedStatement state = this.conn.prepareStatement(sql);
            state.setString(1, idplgn);
            ResultSet hasil = state.executeQuery();
            if(hasil.next()){
                return hasil;
            }else{
                return null;
            }
        }catch(Exception er){
            return null;
        }
    }
    
    public ResultSet CariBarang(String kd_brg){
        try{
            String sql = "SELECT * FROM barang WHERE kd_brg = ?";
            PreparedStatement state = this.conn.prepareStatement(sql);
            state.setString(1, kd_brg);
            ResultSet hasil = state.executeQuery();
            if(hasil.next()){
                return hasil;
            }else{
                return null;
            }
        }catch(Exception er){
            return null;
        }
    }
    
    public boolean InsertNota(String id, String idplgn, String idKasir){
        try{
            String sql = "INSERT INTO nota  (id_nota,tgl_nota,id_plgn,id_kasir) VALUES (?,?,?,?)";
            PreparedStatement state = this.conn.prepareStatement(sql);
            ZoneId zoneId = ZoneId.of("Asia/Jakarta");
            ZonedDateTime nowWIB = ZonedDateTime.now(zoneId);
            Timestamp timestamp = Timestamp.from(nowWIB.toInstant());
            state.setString(1, id);
            state.setTimestamp(2, timestamp);
            state.setInt(3, Integer.valueOf(idplgn));
            state.setInt(4, Integer.valueOf(idKasir));
            int result = state.executeUpdate();
            return result >0;
            
        }catch(Exception err){
            System.out.println(err);
            return false;
            
        }
    }
        
    
}
