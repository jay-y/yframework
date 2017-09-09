package org.yframework.system.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.yframework.mybatis.auditing.domain.AbstractAuditingEntity;
import org.yframework.system.config.Constants;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.Locale;

/**
 * A user.
 */
@Entity
@Table(name = "sys_user")
public class User extends AbstractAuditingEntity<String>
{
    private static final long serialVersionUID = 1L;

    @NotNull
    @Pattern(regexp = Constants.LOGIN_REGEX)
    @Size(min = 1, max = 50)
    @Column(name = "login")
    private String login;

    @JsonIgnore
    @NotNull
    @Size(min = 60, max = 60)
    @Column(name = "password_hash")
    private String password;

    @Size(max = 50)
    @Column(name = "first_name")
    private String firstName;

    @Size(max = 50)
    @Column(name = "last_name")
    private String lastName;

    @Size(max = 100)
    @Column(name = "email")
    private String email;

    @Size(min = 2, max = 5)
    @Column(name = "lang_key")
    private String langKey;

    @Size(max = 256)
    @Column(name = "image_url")
    private String imageUrl;

    @Size(max = 20)
    @JsonIgnore
    @Column(name = "activation_key")
    private String activationKey;

    @Size(max = 20)
    @JsonIgnore
    @Column(name = "reset_key")
    private String resetKey;

    @Column(name = "reset_date")
    private Instant resetDate = null;

    public User()
    {
    }

    public User(String id)
    {
        super(id);
    }

    public String getLogin()
    {
        return login;
    }

    public void setLogin(String login)
    {
        this.login = null != login ? login.toLowerCase(Locale.ENGLISH) : null;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getImageUrl()
    {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl)
    {
        this.imageUrl = imageUrl;
    }

    public String getActivationKey()
    {
        return activationKey;
    }

    public void setActivationKey(String activationKey)
    {
        this.activationKey = activationKey;
    }

    public String getResetKey()
    {
        return resetKey;
    }

    public void setResetKey(String resetKey)
    {
        this.resetKey = resetKey;
    }

    public Instant getResetDate()
    {
        return resetDate;
    }

    public void setResetDate(Instant resetDate)
    {
        this.resetDate = resetDate;
    }

    public String getLangKey()
    {
        return langKey;
    }

    public void setLangKey(String langKey)
    {
        this.langKey = langKey;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }

        User user = (User) o;

        return login.equals(user.login);
    }

    @Override
    public int hashCode()
    {
        return login.hashCode();
    }

    @Override
    public String toString()
    {
        return "User{" + "login='" + login + '\'' + ", password='" + password + '\'' + ", firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", email='" + email + '\'' + ", langKey='" + langKey + '\'' + ", imageUrl='" + imageUrl + '\'' + ", activationKey='" + activationKey + '\'' + ", resetKey='" + resetKey + '\'' + ", resetDate=" + resetDate + "} " + super.toString();
    }
}
