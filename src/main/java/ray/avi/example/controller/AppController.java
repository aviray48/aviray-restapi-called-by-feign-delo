package ray.avi.example.controller;

import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ray.avi.common.exception.GeneralRuntimeException;
import ray.avi.common.util.UtilMethods;
import ray.avi.common.vo.GeneralBase;
import ray.avi.common.vo.SimpleMessageObject;
import ray.avi.example.service.AppService;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping(value={"/calledByFeign"})
public class AppController {

	AppService appService;

	@GetMapping(value = "/logtest", produces = { "application/json", "application/xml" })
	public SimpleMessageObject logTest(@RequestHeader HttpHeaders headers) throws Exception {
		log.trace("{}|trace (audit) log", UtilMethods.getMethodName());
		log.debug("{}|debug log", UtilMethods.getMethodName());
		log.info("{}|info log", UtilMethods.getMethodName());
		log.warn("{}|warn log", UtilMethods.getMethodName());
		log.error("{}|error log", UtilMethods.getMethodName());
		SimpleMessageObject simpleMessageObject = new SimpleMessageObject();
		simpleMessageObject.setResult(true);
		simpleMessageObject.setTestName(this.getClass().getSimpleName() + "." + UtilMethods.getMethodName());
		simpleMessageObject.setAdditionalInfo("Testing of closeConnectionTest logging functionality is complete");
		return simpleMessageObject;
	}
	
	@GetMapping(value = "/getObjectListFromFeign", produces = { "application/json", "application/xml" })
	public List<GeneralBase> getObjectListFromFeign(@RequestHeader HttpHeaders headers) {
		log.error("{}|start", UtilMethods.getMethodName());
		return appService.getObjectListFromFeign(headers);
	}
	
	@GetMapping(value = "/getIntentionalError", produces = { "application/json", "application/xml" })
	public Object getIntentionalError(@RequestHeader HttpHeaders headers) {
		log.error("{}|start", UtilMethods.getMethodName());
		throw new GeneralRuntimeException("Error Intentionally Thrown");
	}
	
    @GetMapping(value = "/SQLServerGet", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<GeneralBase> getFromSQLServer(@RequestHeader HttpHeaders headers) {
		log.error("{}|start", UtilMethods.getMethodName());
		return new ResponseEntity<>(appService.getFromSQLServer(), HttpStatus.OK);
	}
    
}