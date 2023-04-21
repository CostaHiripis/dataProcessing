package org.costandino.dataProcessing.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.core.report.ProcessingMessage;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import org.costandino.dataProcessing.domain.agriculture.Agriculture;
import org.costandino.dataProcessing.domain.agriculture.Agricultures;
import org.costandino.dataProcessing.domain.DomainEntity;
import org.costandino.dataProcessing.domain.co2Emission.Co2Emission;
import org.costandino.dataProcessing.domain.co2Emission.Co2Emissions;
import org.costandino.dataProcessing.domain.globalSeaLevel.GlobalSeaLevel;
import org.costandino.dataProcessing.domain.globalSeaLevel.GlobalSeaLevels;
import org.costandino.dataProcessing.exception.SchemaException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.xml.sax.SAXException;

import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static javax.xml.XMLConstants.W3C_XML_SCHEMA_NS_URI;

@ControllerAdvice
public abstract class GlobalRestHandler {

    public boolean isEntityValidXml(DomainEntity entity, File xmlSchema, String className) {
        try {
            XmlMapper xmlMapper = new XmlMapper();
            String xml;
            xml = xmlMapper.writeValueAsString(entity);
            switch (className) {
                case "Agriculture" ->
                        xml =  xml.replaceFirst("<agriculture>", "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n <agriculture xmlns=\"https://www.w3schools.com\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"https://www.w3schools.com agriculture_schema.xsd\">");
                case "Co2Emission" ->
                        xml =  xml.replaceFirst("<co2Emission>", "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n <co2Emission xmlns=\"https://www.w3schools.com\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"https://www.w3schools.com co2Emissions_schema.xsd\">");
                case "GlobalSeaLevel" ->
                        xml =  xml.replaceFirst("<globalSeaLevel>", "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n <globalSeaLevel xmlns=\"https://www.w3schools.com\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"https://www.w3schools.com globalSeaLevel_schema.xsd\">");
            }
            SchemaFactory schemaFactory = SchemaFactory.newInstance(W3C_XML_SCHEMA_NS_URI);
            Schema schema = schemaFactory.newSchema(xmlSchema);
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new ByteArrayInputStream(xml.getBytes((StandardCharsets.UTF_8)))));
            return true;
        } catch ( SAXException e) {
            throw new SchemaException(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();

        }
        return false;
    }

    public boolean isEntityValidJson(DomainEntity domainEntity, File jsonSchemaFile) {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonSchemaFactory jsonSchemaFactory = JsonSchemaFactory.byDefault();

        URI uri = jsonSchemaFile.toURI();
        JsonNode jsonNode = objectMapper.valueToTree(domainEntity);

        try
        {
            ProcessingReport report = jsonSchemaFactory.getJsonSchema(uri.toString()).validate(jsonNode);
            if (report.isSuccess())
            {
                return true;
            }
            else
            {
                List<String> errors = new ArrayList<>();
                for (ProcessingMessage processingMessage: report) {
                    JsonNode jsonError = processingMessage.asJson();
                    var instance = jsonError.get("instance").findValue("pointer")
                            .asText().replaceFirst("/", "");
                    errors.add(instance + ": " + processingMessage.getMessage());
                }
                throw new SchemaException(errors.toString());
            }

        }
        catch (ProcessingException e)
        {
            e.printStackTrace();
        }
        return false;
    }

    public Agricultures getValidAgriculture(List<Agriculture> agricultureServiceResponse, File schema, String contentType) {
        List<Agriculture> validAgriculture = new ArrayList<>();
        Agricultures agricultures = new Agricultures();
        for (Agriculture agriculture: agricultureServiceResponse) {
            try {
                switch (contentType) {
                    case "application/json" -> isEntityValidJson(agriculture, schema);
                    case "application/xml" -> isEntityValidXml(agriculture, schema, "Agriculture");
                }
                validAgriculture.add(agriculture);
            } catch (SchemaException e) {
                e.printStackTrace();
            }
        }
        agricultures.setAgriculture(validAgriculture);
        return agricultures;
    }

    public GlobalSeaLevels getValidSeaLevel(List<GlobalSeaLevel> globalSeaLevelServiceResponse, File schema, String contentType) {
        List<GlobalSeaLevel> validGlobalSeaLevel = new ArrayList<>();
        GlobalSeaLevels globalSeaLevels = new GlobalSeaLevels();
        for (GlobalSeaLevel globalSeaLevel: globalSeaLevelServiceResponse) {
            try {
                switch (contentType) {
                    case "application/json" -> isEntityValidJson(globalSeaLevel, schema);
                    case "application/xml" -> isEntityValidXml(globalSeaLevel, schema, "GlobalSeaLevel");
                }
                validGlobalSeaLevel.add(globalSeaLevel);
            } catch (SchemaException e) {
                e.printStackTrace();
            }
        }
        globalSeaLevels.setGlobalSeaLevel(validGlobalSeaLevel);
        return globalSeaLevels;
    }

    public Co2Emissions getValidCo2Emissions(List<Co2Emission> co2EmissionServiceResponse, File schema, String contentType) {
        List<Co2Emission> validCo2Emission = new ArrayList<>();
        Co2Emissions co2Emissions = new Co2Emissions();
        for (Co2Emission co2Emission: co2EmissionServiceResponse) {
            try {
                switch (contentType) {
                    case "application/json" -> isEntityValidJson(co2Emission, schema);
                    case "application/xml" -> isEntityValidXml(co2Emission, schema, "Co2Emission");
                }
                validCo2Emission.add(co2Emission);
            } catch (SchemaException e) {
                e.printStackTrace();
            }
        }
        co2Emissions.setCo2Emission(validCo2Emission);
        return co2Emissions;
    }



}
