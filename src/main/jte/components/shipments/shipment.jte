@import com.oway.shipment.model.Shipment
@import com.oway.shipment.model.Shipment.Status

@param Shipment shipment

<div id="shipment-${shipment.getId().toString()}" class="border rounded-md p-4 bg-gray-50 hover:bg-gray-100 transition">

    <div class="flex items-start justify-between">
        <!-- Left: Shipment Info -->
        <div class="flex-1">
            <!-- Shipment Core Info -->
            <div class="font-medium text-gray-900">
                ${shipment.getDestinationZip()}
            </div>

            <div class="text-sm text-gray-600">
                ID: ${shipment.getId().toString()}
            </div>

            <!-- Optional metadata -->
            @if(shipment.getStatus() != null)
                <div class="mt-2">
                    <span class="inline-block text-xs px-2 py-0.5 rounded
                        ${Status.COMPLETED.equals(shipment.getStatus())
                            ? "bg-green-100 text-green-800"
                            : "bg-yellow-100 text-yellow-800"}">
                        ${shipment.getStatus()}
                    </span>
                </div>
            @endif
        </div>

        <!-- Right: Complete Button (only if Pending) -->
        @if(Status.PENDING.equals(shipment.getStatus()))
            <button
                    class="ml-4 px-4 py-2 bg-blue-600 text-white text-sm rounded hover:bg-blue-700 flex-shrink-0"
                    hx-post="/shipments/${shipment.getId().toString()}/complete"
                    hx-target="#shipment-${shipment.getId().toString()}"
                    hx-swap="outerHTML"
            >
                Complete
            </button>
        @endif
    </div>
</div>