package uvg.edu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class VectorHeapTest {

    private VectorHeap<Paciente> heap;

    // Helper method to create patients easily
    private Paciente createPaciente(String name, char code) {
        return new Paciente(name, "Symptom " + code, code);
    }

    @BeforeEach
    void setUp() {
        heap = new VectorHeap<>();
    }

    @Test
    void testAddAndGetFirst() {
        assertTrue(heap.isEmpty(), "Heap should be empty initially.");
        assertEquals(0, heap.size(), "Size should be 0 initially.");
        assertNull(heap.getFirst(), "getFirst should return null on empty heap.");

        Paciente pC = createPaciente("Patient C", 'C');
        Paciente pA = createPaciente("Patient A", 'A');
        Paciente pB = createPaciente("Patient B", 'B');

        heap.add(pC);
        assertEquals(pC, heap.getFirst(), "First element should be C after adding C.");
        assertEquals(1, heap.size());

        heap.add(pA); // Higher priority
        assertEquals(pA, heap.getFirst(), "First element should be A after adding A.");
        assertEquals(2, heap.size());


        heap.add(pB); // Medium priority
        assertEquals(pA, heap.getFirst(), "First element should still be A after adding B.");
        assertEquals(3, heap.size());
        assertFalse(heap.isEmpty());
    }

    @Test
    void testRemove() {
        Paciente pC = createPaciente("Patient C", 'C');
        Paciente pA1 = createPaciente("Patient A1", 'A');
        Paciente pB = createPaciente("Patient B", 'B');
        Paciente pE = createPaciente("Patient E", 'E');
        Paciente pA2 = createPaciente("Patient A2", 'A'); // Same priority as A1

        heap.add(pC);
        heap.add(pA1);
        heap.add(pB);
        heap.add(pE);
        heap.add(pA2);

        assertEquals(5, heap.size());

        // Removing elements should yield them in priority order (A, A, B, C, E)
        Paciente removed1 = heap.remove();
        assertTrue(removed1 == pA1 || removed1 == pA2, "First removed should be an A patient.");
        assertEquals(4, heap.size());

        Paciente removed2 = heap.remove();
        assertTrue(removed2 == pA1 || removed2 == pA2, "Second removed should be the other A patient.");
        assertNotEquals(removed1, removed2, "Removed A patients should be distinct objects.");
        assertEquals(3, heap.size());

        assertEquals(pB, heap.remove(), "Third removed should be B.");
        assertEquals(2, heap.size());

        assertEquals(pC, heap.remove(), "Fourth removed should be C.");
        assertEquals(1, heap.size());

        assertEquals(pE, heap.remove(), "Fifth removed should be E.");
        assertEquals(0, heap.size());
        assertTrue(heap.isEmpty());

        assertNull(heap.remove(), "Remove on empty heap should return null.");
    }

    @Test
    void testAddNull() {
        assertThrows(NullPointerException.class, () -> {
            heap.add(null);
        }, "Adding null should throw NullPointerException.");
    }

    @Test
    void testClear() {
        heap.add(createPaciente("P1", 'C'));
        heap.add(createPaciente("P2", 'A'));
        assertFalse(heap.isEmpty());
        heap.clear();
        assertTrue(heap.isEmpty());
        assertEquals(0, heap.size());
        assertNull(heap.getFirst());
    }

}