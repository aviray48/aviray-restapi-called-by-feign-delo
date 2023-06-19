package ray.avi.example.service;

import lombok.AllArgsConstructor;
import java.util.Arrays;
import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import ray.avi.common.vo.GeneralBase;
import ray.avi.common.util.UtilMethods;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
public class AppService {

	public List<GeneralBase> getObjectListFromFeign(HttpHeaders headers) {
		log.error("{}|start", UtilMethods.getMethodName());
		GeneralBase gbOne = new GeneralBase();
		gbOne.setGeneralId(19L);
		gbOne.setGeneralNumber("nineteen");
		gbOne.setOperationalCountryCode("CHUL");
		return Arrays.asList(gbOne, new GeneralBase());
	}
	
	public GeneralBase getFromSQLServer() {
		log.error("{}|start", UtilMethods.getMethodName());
		return new GeneralBase();
	}
	
}
