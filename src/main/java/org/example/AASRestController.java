package org.example;

import org.eclipse.basyx.submodel.restapi.SubmodelProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/aas")
public class AASRestController {

    private Map<String, ISubmodelFactory> submodelFactories = new HashMap<>();

    public void addSubmodelFactory(String submodelIdShort, ISubmodelFactory submodelFactory) {
        this.submodelFactories.put(submodelIdShort, submodelFactory);
    }

    @RequestMapping(value = "{aasIdShort}/submodels/{submodelIdShort}/submodel/**", method = RequestMethod.GET)
    public ResponseEntity<Object> testRestEndpoint(
            @PathVariable(name = "aasIdShort") String aasIdShort,
            @PathVariable(name = "submodelIdShort") String submodelId,
            HttpServletRequest request) {
        var path = request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE).toString();
        var restOfPath = path.replace("/aas/" + aasIdShort + "/submodels/" + submodelId, "");

        if (this.submodelFactories.containsKey(submodelId)) {
            var submodelFactory = submodelFactories.get(submodelId);
            var submodel = submodelFactory.createSubmodel();
            var submodelProvider = new SubmodelProvider(submodel);

            return ResponseEntity.ok(submodelProvider.getValue(restOfPath));
        } else {
            var errorMessage = "Submodel [idShort='" + submodelId +"'] for AAS [idShort='" + aasIdShort + "'] not found";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }

}
