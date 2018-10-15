package com.cust.system.service.dto;

import com.cust.common.config.Constants;
import com.cust.system.domain.User;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.yframework.mybatis.auditing.service.dto.AuditingEntityDTO;
import org.yframework.toolkit.json.jackson.InstantDeserializer;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.List;

/**
 * A DTO representing a user, with his authorities.
 */
public class UserDTO implements AuditingEntityDTO<String>
{
    private static final long serialVersionUID = 1L;

    private String id;

    @NotBlank
    @Pattern(regexp = Constants.LOGIN_REGEX)
    @Size(min = 1, max = 50)
    private String login;

    @Size(max = 50)
    private String firstName;

    @Size(max = 50)
    private String lastName;

    @Email
    @Size(min = 5, max = 100)
    private String email;

    private String address;

    private String telephone;

    @Size(min = 2, max = 5)
    private String langKey;

    @Size(max = 256)
    private String imageUrl;

    private boolean activated = true;

    private List<String> authorities;

    private String password;

    private String oldPassWord;

    @JsonDeserialize(using = InstantDeserializer.class)
    private Instant createdDate;

    private String resetKey;

    @JsonDeserialize(using = InstantDeserializer.class)
    private Instant resetDate;

    private String activationKey;

    public UserDTO()
    {
        // Empty constructor needed for Jackson.
    }

    public UserDTO(String id)
    {
        this.id = id;
    }

    public UserDTO(String id, String login, String firstName, String lastName, String email, String address, String telephone, String langKey, String imageUrl, boolean activated, String password, String resetKey, Instant resetDate, String activationKey)
    {
        this.id = id;
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.telephone = telephone;
        this.langKey = langKey;
        this.imageUrl = imageUrl;
        this.activated = activated;
        this.password = password;
        this.resetKey = resetKey;
        this.resetDate = resetDate;
        this.activationKey = activationKey;
    }

    public UserDTO(User user)
    {
        this(user.getId(), user.getLogin(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getAddress(), user.getTelephone(), user.getLangKey(), user.getImageUrl(), user.isActivated(), user.getPassword(), user.getResetKey(), user.getResetDate(), user.getActivationKey());
    }

    @Override
    public String getId()
    {
        return id;
    }

    @Override
    public void setId(String id)
    {
        this.id = id;
    }

    public String getLogin()
    {
        return login;
    }

    public void setLogin(String login)
    {
        this.login = login;
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

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getTelephone()
    {
        return telephone;
    }

    public void setTelephone(String telephone)
    {
        this.telephone = telephone;
    }

    public String getImageUrl()
    {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl)
    {
        this.imageUrl = imageUrl;
    }

    public boolean isActivated()
    {
        return activated;
    }

    public void setActivated(boolean activated)
    {
        this.activated = activated;
    }

    public String getLangKey()
    {
        return langKey;
    }

    public void setLangKey(String langKey)
    {
        this.langKey = langKey;
    }

    public List<String> getAuthorities()
    {
        return authorities;
    }

    public void setAuthorities(List<String> authorities)
    {
        this.authorities = authorities;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getOldPassWord()
    {
        return oldPassWord;
    }

    public void setOldPassWord(String oldPassWord)
    {
        this.oldPassWord = oldPassWord;
    }

    public Instant getCreatedDate()
    {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate)
    {
        this.createdDate = createdDate;
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

    public String getActivationKey()
    {
        return activationKey;
    }

    public void setActivationKey(String activationKey)
    {
        this.activationKey = activationKey;
    }

    @Override
    public String toString()
    {
        return "UserDTO{" + "id='" + id + '\'' + ", login='" + login + '\'' + ", firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", email='" + email + '\'' + ", address='" + address + '\'' + ", telephone='" + telephone + '\'' + ", langKey='" + langKey + '\'' + ", imageUrl='" + imageUrl + '\'' + ", activated=" + activated + ", authorities=" + authorities + ", password='" + password + '\'' + ", oldPassWord='" + oldPassWord + '\'' + ", createdDate=" + createdDate + ", resetKey='" + resetKey + '\'' + ", resetDate=" + resetDate + ", activationKey='" + activationKey + '\'' + '}';
    }
}
