<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<t:_layout>
	<jsp:attribute name="header">
    </jsp:attribute>
	<jsp:attribute name="footer">
    </jsp:attribute>
	<jsp:body>
        <t:Navbar></t:Navbar>
    	<div class="container">
			<div align="center">
			    <h2>${resselectedArea.area_Name}</h2>
			    <form action="${pageContext.request.contextPath}/ReservationsController?makeReservation" method="post">
			     		<div class="row">
				        <div class="well col-xs-10 col-sm-10 col-md-6 col-xs-offset-1 col-sm-offset-1 col-md-offset-3">
				            <div class="row">
				                <div class="col-xs-6 col-sm-6 col-md-6">
				                    <address>
				                        <h2>${resSelectedArea.area_Name}</h2>
				                        <br>
				                        Floor: ${resselectedFloorNumber}
				                        <br>
				                        Permit Type: ${resselectedPermitType}
				                        <br>
				                    </address>
				                </div>
				                <div class="col-xs-6 col-sm-6 col-md-6 text-right">
				                    <p>
				                        <em>Time: ${resselectedStartTime} To ${resselectedEndTime }</em>
				                    </p>
				                    <p>
				                        <em>Spot ID : ${resspotID}</em>
				                    </p>
				                </div>
				            </div>
				            <div class="row">
				                <div class="text-center">
				                    <h3>Review & Pay</h3>
				                </div>
				                </span>
				                <table class="table table-hover">
				                    <thead>
				                        <tr>
				                            <th>Option</th>
				
				                            <th class="text-center">Total</th>
				                        </tr>
				                    </thead>
				                    <tbody>
				                        <tr>
				                            <td class="col-md-9"><em>Cart</em></h4></td>
				
				                            <td class="col-md-1 text-center">$${finalCartPrice}</td>
				                        </tr>
				                        <tr>
				                            <td class="col-md-9"><em>Camera</em></h4></td>
				                            <td class="col-md-1 text-center">$${finalCameraPrice}</td>
				                        </tr>
				                        <tr>
				                            <td class="col-md-9"><em>History</em></h4></td>
				                            <td class="col-md-1 text-center">$${finalHistoryPrice}</td>
				                        </tr>
				                        <tr>
				                            <td class="text-center">
				                            <p>
				                                <strong>Tax: </strong>
				                            </p></td>
				                            <td class="text-center">
				                            <p>
				                                <strong>$${finalTax}</strong>
				                            </p></td>
				                        </tr>
				                        <tr>
				                            <td class="text-right"><h4><strong>Total: </strong></h4></td>
				                            <td class="text-center text-danger"><strong> $${finalTotal}</strong></h4></td>
				                        </tr>
				                        <tr>
				                            <td class="col-md-4"><em>Card Number</em></h4></td>
				                            <td class="col-md-8">
				                            <input type="text" class="form-control" name="cardNumber" id="cardNumber" placeholder="Valid Card Number" autofocus value="<c:out value='${creditcard.cardNumber}'/>" />
				                        	<input disabled class="form-control center_div" name="cardNumberError" id="cardNumberError" value="<c:out value='${creditcarderrorMsgs.cardNumberError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"   maxlength="60">
				                        </td>
				                        </tr>
				                        <tr>
				                            <td class="col-md-9"><em>Card Type</em></h4></td>
				                            <td class="col-md-1 text-center">
				                            <select class="form-control" name="cardType" id="cardType">
				                              <c:forEach var="cardTypeValue" items="${allCardTypes}">
													   <option value="${cardTypeValue}"
													           <c:if test="${cardTypeValue eq selectedcreditcard}">selected="selected"</c:if>
													     >
													        ${cardTypeValue}
													    </option>
										     	</c:forEach>
											  </select>
											  </td>
				                        </tr>
				                        <tr>
				                            <td class="col-md-9"><em>Expiry Date</em></h4></td>
				                            <td class="col-md-1 text-center">
				                            <div class="col-xs-6 col-lg-6 pl-ziro">
                                    			<input type="text" class="form-control" name="expiryMonth" id="expiryMonth" placeholder="MM" value="<c:out value='${creditcard.month}'/>"  />
                                    			<input disabled class="form-control center_div" name="monthError" id="monthError"  value="<c:out value='${creditcarderrorMsgs.monthError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"   maxlength="60">
                                    			<input type="text" class="form-control" name="expiryYear" id="expiryYear" placeholder="YYYY" value="<c:out value='${creditcard.year}'/>" />
                                    			<input disabled class="form-control center_div" name="yearError" id="yearError"  value="<c:out value='${creditcarderrorMsgs.yearError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"   maxlength="60">
                                			</div>
                                    		</td>
				                        </tr>
				                        <tr>
				                            <td class="col-md-9">CVV</td>
				                            <td class="col-md-1 text-center">
				                            <input type="password" class="form-control" name="cvvCode" id="cvvCode" placeholder="CVV" value="<c:out value='${creditcard.cvv}'/>" />
				                            <input disabled class="form-control center_div" name="cvvError" id="cvvError"  value="<c:out value='${creditcarderrorMsgs.cvvError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"   maxlength="60">                                 			
				                        	</td>
				                        </tr>
				                    </tbody>
				                </table>
				                
				                <input id="btnPayReserve" type="submit" class="btn btn-success btn-lg btn-block" value="Pay & Reserve">
				                       <span class="glyphicon glyphicon-chevron-right"></span>
				                <input name="action" value="makeReservation" type="hidden">
				                <input name="total" value="${finalTotal}" type="hidden">
								
				            </div>
				        </div>
					
		    </form>
			</div>
<!-- 			<button class="btn btn-secondary" type="button" name="back" onclick="history.back()">Back</button>			 -->
				
		</div>
		
		<div class="container">
    
    </div>
    </jsp:body>
</t:_layout>
