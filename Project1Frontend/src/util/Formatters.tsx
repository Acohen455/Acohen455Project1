export const handleMoneyInput = (value: string, setAmount: (val: string) => void) => {
    // Remove non-numeric characters except periods
    value = value.replace(/[^0-9.]/g, "");

    // Prevent multiple decimal points
    if ((value.match(/\./g) || []).length > 1) {
        return;
    }

    // Ensure proper decimal places
    const parts = value.split(".");
    if (parts.length === 2 && parts[1].length > 2) {
        parts[1] = parts[1].slice(0, 2); // Limit to 2 decimal places
        value = parts.join(".");
    }

    setAmount(value);
};
