<#assign package=model.variables.package>
<#assign class=model.variables.class>
<#assign classVar=model.variables.classVar>
<#assign system=vars.system>
package com.${system}.${package}.service;

import java.io.Serializable;
import com.hotent.base.manager.api.Manager;
import com.${system}.${package}.model.${class};


public interface ${class}Service < PK extends Serializable,T extends ${class}> extends BaseService< PK, T>{
	
}

