/**
 * (C) Copyright IBM Corporation 2016.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.wasdev.rtcommsipgateway.servlets;

import java.io.IOException;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.sip.*;
import net.wasdev.rtcommsipgateway.utils.SipUtils;

@javax.servlet.sip.annotation.SipServlet(description = "The main servlet for the gateway application", name = "WebRTCGatewayServlet", loadOnStartup=1)
public class WebRTCGatewayServlet extends SipServlet{

	private static Logger log = Logger.getLogger(WebRTCGatewayServlet.class.getName());

	private static final long serialVersionUID = 1538727590067323890L;

	@Override
	public void init() throws ServletException {
	}

	/**
	 * Setup a call record and starting the call scenario to the invited endpoint.
	 */
	protected void doInvite(SipServletRequest req) throws ServletException,
	IOException {
		System.out.println("####### Called DoInvite" + req.isInitial());
		if (req.isInitial()) {
			Proxy proxy = req.getProxy();
			proxy.setRecordRoute(true);
			proxy.setSupervised(true);
			proxy.proxyTo(req.getRequestURI()); // bobs uri
		}
	}

	/**
	 * Handles response from invited endpoint and send the answer to the media server.
	 * After media server will process the answer, the call will start.
	 */
	@Override
	protected void doSuccessResponse(SipServletResponse resp) throws ServletException,
	IOException {
		//IMPLEMENT THIS
	}

	@Override
	protected void doProvisionalResponse(SipServletResponse resp) throws ServletException, IOException {

	}

	@Override
	protected void doErrorResponse(SipServletResponse resp)
			throws ServletException, IOException {

	}

	@Override
	protected void doInfo(SipServletRequest req) throws ServletException, IOException {

	}

	/**
	 * Terminate call request was received.
	 * Clean media server resources and connections for the call record.
	 */
	@Override
	protected void doBye(SipServletRequest req) throws ServletException,
	IOException {
		System.out.println("SimpleProxyServlet: Got BYE request:");
		super.doBye(req);
	}

	@Override
	protected void doCancel(SipServletRequest req) throws ServletException, IOException {

	}

	@Override
	protected void doResponse(SipServletResponse response)
			throws ServletException, IOException {

		System.out.println("SimpleProxyServlet: Got response:");
		super.doResponse(response);
	}

	@Override
	public void destroy() {

	}

	@Override
	protected void doAck(SipServletRequest req) throws ServletException, IOException {
		System.out.println("SimpleProxyServlet: doAck:" + req);
		super.doAck(req);
	}
}
