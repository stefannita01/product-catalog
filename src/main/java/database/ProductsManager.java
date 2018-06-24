/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import entities.Product;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import ui.LoginForm;


/**
 *
 * @author Stefan
 */

public class ProductsManager {
    public static void addProduct(Product product) {
        Connection conn = ConnectionManager.getConnection();
        
        String sql = "insert into products " +
                "(name, brand, category, price, availability, imagepath) " +
                "values(?, ?, ?, ?, ?, ?)";
        
        PreparedStatement createStatement = null;
        try {
            createStatement = conn.prepareStatement(sql);
            createStatement.setString(1, product.getName());
            createStatement.setString(2, product.getBrand());
            createStatement.setString(3, product.getCategory());
            createStatement.setFloat(4, product.getPrice());
            createStatement.setInt(5, product.getStock());
            createStatement.setString(6, product.getImagePath());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        try {
            createStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductsManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public static void removeProduct(Product product) {
        Connection conn = ConnectionManager.getConnection();
        
        String sql = "delete from products where id = ?";
        
        PreparedStatement removeStatement = null;
        try {
            removeStatement = conn.prepareStatement(sql);
            removeStatement.setInt(1, product.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        try {
            removeStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductsManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public static void updateProduct(Product current, Product updated) {
        Connection conn = ConnectionManager.getConnection();
        
        String sql = "update products set id = ?, name = ?, brand = ?, " + 
                "category = ?, price = ?, availability = ?, imagepath =?" + 
                "where id = ?";
        
        PreparedStatement updateStatement = null;
        try {
            updateStatement = conn.prepareStatement(sql);
            updateStatement.setInt(1, updated.getId());
            updateStatement.setString(2, updated.getName());
            updateStatement.setString(3, updated.getBrand());
            updateStatement.setString(4, updated.getCategory());
            updateStatement.setFloat(5, updated.getPrice());
            updateStatement.setInt(6, updated.getStock());
            updateStatement.setString(7, updated.getImagePath());
            updateStatement.setInt(8, current.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        try {
            updateStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductsManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static ResultSet showAll() {
        String query = "select * from products";
        Connection conn = ConnectionManager.getConnection();
        
        PreparedStatement searchStatement = null;
        try {
            searchStatement = conn.prepareStatement(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        ResultSet results = null;
        
        try {
            results = searchStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return results;
        
    }
    
    public static ResultSet searchProductsByName(String keyword, 
            String sortParam) {
        String query = "select * from products where name like ?";
        if (!sortParam.equals("None")) {
            query += " order by " + sortParam;
        }
        Connection conn = ConnectionManager.getConnection();
        
        PreparedStatement searchStatement = null;
        try {
            searchStatement = conn.prepareStatement(query);
            
            searchStatement.setString(1, "%" + keyword + "%");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        ResultSet results = null;
        
        try {
            results = searchStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
       
        return results;
    }
    
    public static ResultSet searchProductsByBrand(String keyword, 
            String sortParam) {
        String query = "select * from products where brand like ?";
        if (!sortParam.equals("None")) {
            query += " order by " + sortParam;
        }
        Connection conn = ConnectionManager.getConnection();
        
        PreparedStatement searchStatement = null;
        try {
            searchStatement = conn.prepareStatement(query);
            
            searchStatement.setString(1, "%" + keyword + "%");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        ResultSet results = null;
        
        try {
            results = searchStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return results;
    }
    
    public static ResultSet searchProductsByCategory(String keyword, 
            String sortParam) {
        String query = "select * from products where category like ?";
        if (!sortParam.equals("None")) {
            query += " order by " + sortParam;
        }
        Connection conn = ConnectionManager.getConnection();
        
        PreparedStatement searchStatement = null;
        try {
            searchStatement = conn.prepareStatement(query);
            
            searchStatement.setString(1, "%" + keyword + "%");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        ResultSet results = null;
        
        try {
            results = searchStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return results;
    }
    
    public static ResultSet searchProducts(String searchField, String keyword, 
            String sortParam) {
        if (LoginForm.getCurrentUser().isAdmin() &&  
                (searchField.equals(""))) {
            return showAll();
        }
        
        if (searchField.equals("Name")) {
            return searchProductsByName(keyword, sortParam);
        } else if (searchField.equals("Brand")) {
            return searchProductsByBrand(keyword, sortParam);
        } else {
            return searchProductsByCategory(keyword, sortParam);
        }
    }
    
    public static ArrayList<Product> getProductArray(ResultSet productSet) {
        ArrayList<Product> products = new ArrayList<>();
        
        try {
            while(productSet.next()) {
                int id = productSet.getInt("id");
                String name = productSet.getString("Name");
                String brand = productSet.getString("Brand");
                String category = productSet.getString("Category");
                float price = productSet.getFloat("Price");
                int stock = productSet.getInt("availability");
                String imagePath = productSet.getString("imagepath");
                
                Product product = new Product(id, name, brand, category, price,
                stock, imagePath);
                
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return products;
    }
}
