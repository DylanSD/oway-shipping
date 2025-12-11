@param com.oway.shipment.routing.RoutingService.DisplaySolution displaySolution

    <div class="w-full">

        <!-- Header -->
        <h2 class="text-lg font-semibold mb-4">Routing Shipments</h2>

        <!-- Generate Route for Pending Shipments -->
        <div class="flex justify-end mb-4">
            <a
                    href="/route"
                    class="px-3 py-1 bg-blue-600 text-white text-sm rounded hover:bg-blue-700">
                Generate Pending Route
            </a>
        </div>

        <!-- Route Summary -->
        <div class="mb-6">
            <div class="flex justify-between items-center">
                <div class="text-sm font-medium text-gray-700">
                    Total Route Cost
                </div>
                <div class="text-sm font-semibold text-gray-900">
                    @if (displaySolution == null)
                        0.0
                    @else
                        ${String.format("%.2f", displaySolution.cost())}
                    @endif
                </div>
            </div>

            <div class="text-xs text-gray-500 mt-1">
                (Lower cost indicates a more efficient route)
            </div>
        </div>

        <!-- Shipments in Route Order -->
        <div class="space-y-4">
            @if(displaySolution == null || displaySolution.displayRoute().isEmpty())
                <div class="text-gray-500 text-sm italic">
                    No routing results available.
                </div>
            @else
                @for(com.oway.shipment.routing.RoutingService.DisplayRoute route : displaySolution.displayRoute())
                    @template.components.routing.route(route = route)
                @endfor
            @endif
        </div>

        <!-- Detail panel loaded dynamically via HTMX -->
        <div id="shipment-detail-panel" class="mt-6"></div>
    </div>



