package com.dicka.cloudoauth2resource.service;

import com.dicka.cloudoauth2resource.entity.Person;
import com.dicka.cloudoauth2resource.entity.PersonDetails;
import com.dicka.cloudoauth2resource.exception.ResourceNotAcceptable;
import com.dicka.cloudoauth2resource.repository.PersonDetailsRepository;
import com.dicka.cloudoauth2resource.repository.PersonRepository;
import com.dicka.cloudoauth2resource.request.PersonRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
public class PersonService {

    private final PersonRepository personRepository;
    private final PersonDetailsRepository personDetailsRepository;

    @Autowired
    public PersonService(PersonRepository personRepository, PersonDetailsRepository personDetailsRepository){
        this.personRepository = personRepository;
        this.personDetailsRepository = personDetailsRepository;
    }

    /** check username & email **/
    private boolean isExists(String email, String username){
        int counter = personRepository.findPersonByEmailAndUsername(username, email)
                .size();
        return counter > 0 ? true
                : false;
    }

    /** save new person **/
    public PersonRequest createNewPerson(PersonRequest request, BindingResult bindingResult){
        /** check error **/
        Person person = null;
        PersonRequest personRequest = null;
        if (!bindingResult.hasErrors()){
            if (request.getPassword().equals(request.getConfirmPassword())){

                /** person **/
                person = Person
                        .builder()
                        .active(false)
                        .email(request.getEmail())
                        .username(request.getUsername())
                        .password(request.getPassword())
                        .build();

                /**save person **/
                personRepository.save(person);

                /** person details **/
                PersonDetails personDetails = PersonDetails
                        .builder()
                        .address(request.getAddress())
                        .age(request.getAge())
                        .gender(request.getGender())
                        .instagram(request.getInstagram())
                        .twitter(request.getTwitter())
                        .phoneHome(request.getPhoneHome())
                        .phoneNumber(request.getPhoneNumber())
                        .zipCode(request.getZipCode())
                        .person(person)
                        .build();

                /**save persondetails **/
                personDetailsRepository.save(personDetails);

                /** return data **/
                personRequest = PersonRequest
                        .builder()
                        .username(person.getUsername())
                        .email(person.getEmail())
                        .gender(personDetails.getGender())
                        .address(personDetails.getAddress())
                        .age(personDetails.getAge())
                        .phoneNumber(personDetails.getPhoneNumber())
                        .phoneHome(personDetails.getPhoneHome())
                        .zipCode(personDetails.getZipCode())
                        .instagram(personDetails.getInstagram())
                        .twitter(personDetails.getTwitter())
                        .build();

            }else{
                throw new ResourceNotAcceptable("sorry password not match.");
            }
        }

        return personRequest;
    }
}
