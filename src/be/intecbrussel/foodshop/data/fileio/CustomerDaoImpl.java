package be.intecbrussel.foodshop.data.fileio;

import be.intecbrussel.foodshop.config.FilePath;
import be.intecbrussel.foodshop.data.CustomerDao;
import be.intecbrussel.foodshop.model.Customer;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CustomerDaoImpl implements CustomerDao {
    Customer customer;
    @Override
    public Customer readCustomer() {
        Path path = Paths.get(FilePath.CUSTOMER_FILEPATH.getFile().getPath());

        try(BufferedReader reader = Files.newBufferedReader(path)) {
            String line;

            while((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                customer.setName(parts[0]);
                customer.setID(Integer.valueOf(parts[1]));
                customer.setEmail(parts[2]);
                customer.setMoney(Integer.parseInt(parts[1]));
            }

        } catch (IOException ex){
            System.out.println("Oops, something went wrong!");
            System.out.println(ex.getMessage());
        }

        return customer;
    }

    @Override
    public void writeCustomer(Customer customer) {
        try (FileWriter fileWriter = new FileWriter(FilePath.CUSTOMER_FILEPATH.getFile(), false)) {
            fileWriter.write(customer.getName() + " " + customer.getID() + " " + customer.getEmail() + " " + customer.getMoney());
            fileWriter.close(); // try with resources zorgt ervoor dat data automatisch wordt opgeslaan bij sluiten van programma, .close() is overbodig.
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
