package com.beingapple.webservice.auth.ajax;

import com.beingapple.webservice.domain.MemberRequestDTO;
import com.beingapple.webservice.domain.Response;
import com.fasterxml.classmate.TypeResolver;
import com.google.common.collect.Multimap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import springfox.documentation.builders.ApiListingBuilder;
import springfox.documentation.builders.OperationBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiDescription;
import springfox.documentation.service.ApiListing;
import springfox.documentation.service.Operation;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spring.web.plugins.DocumentationPluginsManager;
import springfox.documentation.spring.web.readers.operation.CachingOperationNameGenerator;
import springfox.documentation.spring.web.scanners.ApiDescriptionReader;
import springfox.documentation.spring.web.scanners.ApiListingScanner;
import springfox.documentation.spring.web.scanners.ApiListingScanningContext;
import springfox.documentation.spring.web.scanners.ApiModelReader;

import java.util.*;

public class ApiLoginOperations extends ApiListingScanner {
    @Autowired
    private TypeResolver typeResolver;

    @Autowired
    public ApiLoginOperations(ApiDescriptionReader apiDescriptionReader, ApiModelReader apiModelReader, DocumentationPluginsManager pluginsManager){
        super(apiDescriptionReader, apiModelReader, pluginsManager);
    }

    @Override
    public Multimap<String, ApiListing> scan(ApiListingScanningContext context){
        final Multimap<String, ApiListing> def = super.scan(context);
        final List<ApiDescription> apis = new LinkedList<>();
        final List<Operation> operations = new ArrayList<>();
        final Set<ResponseMessage> response = new HashSet<>();

        response.add(new ResponseMessageBuilder().code(200).message("OK").responseModel(new ModelRef("Response")).build());
        response.add(new ResponseMessageBuilder().code(400).message("Bad Request").build());
        operations.add(new OperationBuilder(new CachingOperationNameGenerator())
                .method((HttpMethod.POST))
                .uniqueId("login")
                .parameters(
                        Arrays.asList(
                                new ParameterBuilder()
                                        .name("dto")
                                        .description("The userId and password")
                                        .parameterType("body")
                                        .type(typeResolver.resolve(MemberRequestDTO.class))
                                        .modelRef(new ModelRef("MemberRequestDTO"))
                                        .required(true)
                                        .build()
                        ))
                .responseMessages(response)
                .summary("Log in")
                .notes("Here you can log in get Access token")
                .build()
        );
        apis.add(new ApiDescription("Authentication","/login", "Authentication Documentation", operations, false));
        def.put("authentication", new ApiListingBuilder(context.getDocumentationContext().getApiDescriptionOrdering())
                .apis(apis)
                .description("Custom authentication")
                .build()
        );

        return def;
    }
}
