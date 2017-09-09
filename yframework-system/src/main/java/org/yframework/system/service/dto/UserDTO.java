package org.yframework.system.service.dto;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.yframework.mybatis.auditing.service.dto.AuditingEntityDTO;
import org.yframework.system.config.Constants;
import org.yframework.system.domain.User;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.Set;

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

    @Size(max = 256)
    private String imageUrl;

    private boolean activated = true;

    @Size(min = 2, max = 5)
    private String langKey;

//    private String createdBy;
//
//    private Instant createdDate;
//
//    private String lastModifiedBy;
//
//    private Instant lastModifiedDate;

//    private Set<String> authorities;

    public UserDTO()
    {
        // Empty constructor needed for Jackson.
    }

    public UserDTO(User user)
    {
        this(user.getId(), user.getLogin(), user.getFirstName(), user.getLastName(), user.getEmail(), user.isActivated(), user.getImageUrl(), user.getLangKey()
//                , user.getCreatedBy(), user.getCreatedDate(), user.getLastModifiedBy(), user.getLastModifiedDate(), user.getAuthorities().stream().map(Authority::getName).collect(Collectors.toSet())
        );
    }

    public UserDTO(String id)
    {
        this.id = id;
    }

    public UserDTO(String id, String login, String firstName, String lastName, String email, boolean activated, String imageUrl, String langKey
//            , String createdBy, Instant createdDate, String lastModifiedBy, Instant lastModifiedDate, Set<String> authorities
    )
    {

        this.id = id;
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.activated = activated;
        this.imageUrl = imageUrl;
        this.langKey = langKey;
//        this.createdBy = createdBy;
//        this.createdDate = createdDate;
//        this.lastModifiedBy = lastModifiedBy;
//        this.lastModifiedDate = lastModifiedDate;
//        this.authorities = authorities;
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

    //    public String getCreatedBy()
//    {
//        return createdBy;
//    }
//
//    public Instant getCreatedDate()
//    {
//        return createdDate;
//    }
//
//    public String getLastModifiedBy()
//    {
//        return lastModifiedBy;
//    }
//
//    public Instant getLastModifiedDate()
//    {
//        return lastModifiedDate;
//    }
//
//    public void setLastModifiedDate(Instant lastModifiedDate)
//    {
//        this.lastModifiedDate = lastModifiedDate;
//    }

//    public Set<String> getAuthorities()
//    {
//        return authorities;
//    }

    @Override
    public String toString()
    {
        return "UserDTO{" + "id='" + id + '\'' + ", login='" + login + '\'' + ", firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", email='" + email + '\'' + ", imageUrl='" + imageUrl + '\'' + ", activated=" + activated + ", langKey='" + langKey + '\'' + '}';
    }
}
