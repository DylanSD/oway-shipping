@import com.oway.shipment.model.Shipment
@import java.util.List

@param List<Shipment> shipments
@param String title

@template.layout.default(
content = @`
<div>

    <!-- Header -->
    <div class="border-b border-gray-200 p-4 flex items-center justify-between">
        <h2 class="text-lg font-semibold">${title}</h2>
    </div>

    <!-- Create Shipment Panel (inline form loads here via HTMX) -->
    <div id="shipment-create-panel" class="border-b border-gray-100"></div>

    <!-- Shipments List Panel -->
    <div class="flex-1 overflow-y-auto p-4">
        <div class="space-y-3">

            @if(shipments == null || shipments.isEmpty())
                <div class="text-gray-500 text-sm italic">
                    No ${title} available.
                </div>
            @else
                @for(Shipment shipment : shipments)
                    @template.components.shipments.shipment(shipment = shipment)
                @endfor
            @endif
        </div>
    </div>

    <!-- Optional detail view panel -->
    <div id="shipment-detail-panel"></div>
</div>
`)