

<div class="max-w-xl mx-auto space-y-6">

    <h2 class="text-xl font-semibold text-gray-700 mb-4">
        Create Shipment
    </h2>

    <form action="/shipments"
          method="POST"
          class="space-y-4">

        <div>
            <label class="block text-sm font-medium mb-1">Origin ZIP</label>
            <input name="originZip" type="text"
                   class="w-full border rounded p-2"
                   required>
        </div>

        <div>
            <label class="block text-sm font-medium mb-1">Destination ZIP</label>
            <input name="destinationZip" type="text"
                   class="w-full border rounded p-2"
                   required>
        </div>

        <div>
            <label class="block text-sm font-medium mb-1">Pallet Count</label>
            <input name="palletCount" type="number"
                   min="1"
                   class="w-full border rounded p-2"
                   required>
        </div>

        <div>
            <label class="block text-sm font-medium mb-1">Weight (lbs)</label>
            <input name="weightLbs" type="number"
                   min="0"
                   step="0.1"
                   class="w-full border rounded p-2"
                   required>
        </div>

        <button type="submit"
                class="px-4 py-2 bg-blue-600 text-white rounded hover:bg-blue-700">
            Save Shipment
        </button>

    </form>
</div>
