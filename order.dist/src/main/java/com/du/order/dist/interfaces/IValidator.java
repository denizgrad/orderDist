package com.du.order.dist.interfaces;

import com.du.order.dist.model.util.ValidationError;
import com.du.order.dist.model.util.transfer.CreateGenelSiparisIn;
import com.du.order.dist.model.util.transfer.GenelSiparisIn;
import com.du.order.dist.model.util.transfer.UpdateGenelSiparisIn;

public interface IValidator {

	public void validate(GenelSiparisIn siparis) throws ValidationError;
}
