@import com.oway.shipment.model.Shipment
@import com.oway.shipment.routing.RoutingService.DisplaySolution
@import java.util.List

@param List<Shipment> shipments
@param DisplaySolution displaySolution
@param String shipmentTitle
@param List<Shipment> route = null
@param String currentPath = "/pending"

@template.layout.default(
content = @`
<div className="min-h-screen bg-[#f9fafb]">

    @template.components.header.header(
        currentPath = currentPath
    )

    <div class="flex h-[calc(100vh-4.5rem)] flex-1">
        <div class="flex w-full">

            @template.components.shipments.sidebar()

            <div id="shipments_panel" class="flex flex-1 flex-col border-r border-gray-200 bg-white text-gray-900">
                @if (currentPath.equals("/create-shipment") )
                    @template.components.shipments.create_shipment()
                @endif
                @if (currentPath.equals("/pending") )
                    @template.components.shipments.shipments(
                        title = shipmentTitle,
                        shipments = shipments
                    )
                @endif
                @if (currentPath.equals("/completed") )
                    @template.components.shipments.shipments(
                    title = shipmentTitle,
                    shipments = shipments
                    )
                @endif
            </div>
            <div class="w-1/3 border-l border-r border-gray-200 bg-white p-4 max-h-screen overflow-y-auto">
                @if (currentPath.equals("/pending") )
                    @template.components.routing.routing(displaySolution = displaySolution)
                @endif
            </div>
        </div>
    </div>

    <!-- Toast notifications container -->
    <div id="toast-container" class="fixed bottom-4 right-4 z-50 space-y-2"></div>
</div>
`)