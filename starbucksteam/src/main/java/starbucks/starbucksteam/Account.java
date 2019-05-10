package starbucks.starbucksteam;

import com.fasterxml.jackson.annotation.JsonFilter;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;


@Validated
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    private String name;

    @NotEmpty
    @Email
    @Column(unique = true, length = 30, nullable = false)
    private String email;

    @NotEmpty
    @Size(min = 6)
    @Column(nullable = false)
    private String password;

    @NotEmpty
    private String address;

    @OneToMany(targetEntity = AccountOrder.class)
    private List<AccountOrder> orders = new ArrayList<>();


    public Account() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<AccountOrder> getOrders() {
        return orders;
    }

    public void setOrders(List<AccountOrder> orders) {
        this.orders = orders;
    }
}

