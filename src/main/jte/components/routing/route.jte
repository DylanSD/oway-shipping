@import com.oway.shipment.routing.RoutingService;

@param RoutingService.DisplayRoute route

<div class="border border-gray-200 rounded-lg p-4 hover:bg-gray-50 transition-colors">

    <!-- Activity Badge and Shipment ID -->
    <div class="flex items-center justify-between mb-2">
        <div class="flex items-center gap-2">
            @if(route.activity().equalsIgnoreCase("pickup"))
                <span class="inline-flex items-center px-2 py-1 text-xs font-medium rounded-full bg-blue-100 text-blue-800">
                    <svg class="w-3 h-3 mr-1" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 10l7-7m0 0l7 7m-7-7v18"/>
                    </svg>
                    Pickup
                </span>
            @else
                <span class="inline-flex items-center px-2 py-1 text-xs font-medium rounded-full bg-green-100 text-green-800">
                    <svg class="w-3 h-3 mr-1" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M19 14l-7 7m0 0l-7-7m7 7V3"/>
                    </svg>
                    Deliver
                </span>
            @endif

            <span class="text-sm font-medium text-gray-900">
                #${route.id()}
            </span>
        </div>
    </div>

    <!-- Arrival Time -->
    <div class="flex items-center text-xs text-gray-600">
        <svg class="w-4 h-4 mr-1" fill="none" stroke="currentColor" viewBox="0 0 24 24">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 8v4l3 3m6-3a9 9 0 11-18 0 9 9 0 0118 0z"/>
        </svg>
        <span>Arrival: ${String.format("%.2f", route.arrTime())}</span>
    </div>
</div>