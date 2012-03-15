package org.codehaus.groovy.grails.plugins.qrcode

import grails.test.*
import org.springframework.core.io.ClassPathResource
import org.apache.commons.io.IOUtils

class QrcodeControllerTests extends GrailsUnitTestCase {
    QrCodeService qrCodeService

    void testSimpleRender() {
        def qrcodeController = new QrcodeController()
        qrcodeController.qrCodeService = qrCodeService
        qrcodeController.request.parameters = [text:'test',size:'30']
        qrcodeController.index()
        byte[] out = qrcodeController.response.contentAsByteArray
        // a 30 by 30 png image of the word "test" in a QRCode
        InputStream pngIn =
            new ClassPathResource("org/codehaus/groovy/grails/plugins/qrcode/qrcode.png").getInputStream()
        byte[] png = IOUtils.toByteArray(pngIn)

        assert png != null
        
        assert Arrays.equals(png,out)
    }
}
